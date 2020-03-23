package com.happyjob.wagesteward.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.UUID;

public class CreateBmpFactory {

    // ----------相机图片的业务相关
    private static final int PHOTO_REQUEST_CAREMA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择

    private Fragment fragment;
    private Activity activity;
    private File tempFile;

    public CreateBmpFactory(Fragment fragment) {
        super();
        this.fragment = fragment;
        WindowManager wm = (WindowManager) fragment.getActivity().getWindowManager();
        windowHeight = wm.getDefaultDisplay().getHeight();
        windowWidth = wm.getDefaultDisplay().getWidth();
    }

    int windowHeight;
    int windowWidth;

    public CreateBmpFactory(Activity activity) {
        super();
        this.activity = activity;
        WindowManager wm = (WindowManager) activity.getWindowManager();
        windowHeight = wm.getDefaultDisplay().getHeight();
        windowWidth = wm.getDefaultDisplay().getWidth();
    }

    public void OpenGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK);//是告诉应用 从列表中选择
        intent.setType("image/*");
        if (fragment != null) {
            fragment.startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
        } else {
            activity.startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
        }
    }

    public void OpenCamera() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (hasSdcard()) {
            tempFile = new File(Environment.getExternalStorageDirectory(), UUID
                    .randomUUID().toString() + ".png");
            Uri uri = Uri.fromFile(tempFile);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        }
        if (fragment != null) {
            fragment.startActivityForResult(intent, PHOTO_REQUEST_CAREMA);
        } else {
            activity.startActivityForResult(intent, PHOTO_REQUEST_CAREMA);
        }
    }

    public String getBitmapFilePath(int requestCode, int resultCode, Intent data) {
        if (requestCode == PHOTO_REQUEST_GALLERY) {
            if (data != null) {
                Uri uri = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                Cursor cursor = null;
                if (fragment != null) {
                    cursor = fragment.getActivity().getContentResolver()
                            .query(uri, filePathColumn, null, null, null);
                } else {
                    cursor = activity.getContentResolver().query(uri,
                            filePathColumn, null, null, null);
                }
                String picturePath;
                if (cursor != null) {
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    picturePath = cursor.getString(columnIndex);
                    cursor.close();
                } else {
                    picturePath = uri.toString().substring(7);
                }
                return picturePath;
            }
        } else if (requestCode == PHOTO_REQUEST_CAREMA) {
            if (hasSdcard()) {
                return tempFile.getAbsolutePath();
            } else {
                if (fragment != null) {
                    Toast.makeText(fragment.getActivity(), "未找到存储卡，无法存储照片！", Toast.LENGTH_SHORT)
                            .show();
                } else {
                    Toast.makeText(activity, "未找到存储卡，无法存储照片！", Toast.LENGTH_SHORT).show();
                }
            }
        }
        return null;
    }

    private int aspectX = 0;
    private int aspectY = 0;

    public Bitmap getBitmapByOpt(String picturePath) {
        Options opt = new Options();
        opt.inJustDecodeBounds = true;
        opt.inPreferredConfig = Config.RGB_565;
        opt.inPurgeable = true;
        Bitmap bitmap = BitmapFactory.decodeFile(picturePath, opt);
        opt.inSampleSize = calculateInSampleSize(opt, 720, 1280);
        opt.inJustDecodeBounds = false;
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
            bitmap = null;
        }
        return BitmapFactory.decodeFile(picturePath, opt);
    }

    //计算图片的缩放值
    public int calculateInSampleSize(Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = (int) Math.ceil((float) height / (float) reqHeight);
            final int widthRatio = (int) Math.ceil((float) width / (float) reqWidth);
            inSampleSize = heightRatio > widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    private Bitmap comp(Bitmap image) {


        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);

        if (baos.toByteArray().length / 1024 > 1024) {//判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出

            baos.reset();//重置baos即清空baos

            image.compress(Bitmap.CompressFormat.JPEG, 50, baos);//这里压缩50%，把压缩后的数据存放到baos中

        }

        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());

        Options newOpts = new Options();

        //开始读入图片，此时把options.inJustDecodeBounds 设回true了

        newOpts.inJustDecodeBounds = true;

        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);

        newOpts.inJustDecodeBounds = false;

        int w = newOpts.outWidth;

        int h = newOpts.outHeight;

        //现在主流手机比较多是800*480分辨率，所以高和宽我们设置为

        float hh = 1280f;//这里设置高度为800f

        float ww = 720f;//这里设置宽度为480f

        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可

        int be = 1;//be=1表示不缩放

        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放

            be = (int) (newOpts.outWidth / ww);

        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放

            be = (int) (newOpts.outHeight / hh);

        }

        if (be <= 0)

            be = 1;

        newOpts.inSampleSize = be;//设置缩放比例

        newOpts.inPreferredConfig = Config.RGB_565;//降低图片从ARGB888到RGB565

        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了

        isBm = new ByteArrayInputStream(baos.toByteArray());

        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);

        return compressImage(bitmap);//压缩好比例大小后再进行质量压缩

    }

    /**
     * 压缩图片
     *
     * @param image
     * @return
     */
    private Bitmap compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 500) {  //循环判断如果压缩后图片是否大于500kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            if (options < 0) {
                options = 0;
            }
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
            options -= 10;//每次都减少10
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
        return bitmap;
    }

    private boolean hasSdcard() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }
}
