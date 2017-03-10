package com.wsywddr.sample.util;

import android.os.Environment;

import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UpProgressHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.storage.UploadOptions;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @date 2015年03月18日
 * @author caoshiyao
 * @Description: 七牛云上传图片
 */
public class QiniuUploadUitls {

	// 保存到外部存储空间(例SD卡)
	private static final String tempJpeg = Environment
			.getExternalStorageDirectory().getPath() + "/" + "temp.jpg";
	private int maxWidth = 720;
	private int maxHeight = 1080;
	private String QNBaseUrl = "http://7te8vz.com1.z0.glb.clouddn.com/";

	// 声明接口
	public interface QiniuUploadUitlsListener {
		// 接口中的三个方法
		public void onSucess(String fileUrl);
		public void onError(int errorCode, String msg);
		public void onProgress(int progress);
	}

	private QiniuUploadUitls() {
	}

	private static QiniuUploadUitls qiniuUploadUitls = null;
	private UploadManager uploadManager = new UploadManager();

	// 获取类的实例对象
	public static QiniuUploadUitls getInstance() {
		if (qiniuUploadUitls == null) {
			qiniuUploadUitls = new QiniuUploadUitls();
		}
		return qiniuUploadUitls;
	}

	/**
	 * 上传到七牛
	 * @param filePath
	 * @param listener
	 */
	@SuppressWarnings("unused")
	public void uploadImage(String filePath, String token,
							final QiniuUploadUitlsListener listener) {

		if (token == null) {
			if (listener != null) {
				listener.onError(-1, "token is null");
			}
			return;
		}
		uploadManager.put(filePath, null, token, new UpCompletionHandler() {
			private String hash;

			@Override
			public void complete(String key, ResponseInfo info,
								 JSONObject response) {

				try {
					// hash就是得到想要的相片ID
					if (response != null) {
						hash = response.getString("hash");
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}

				if (info != null && info.statusCode == 200) {// 上传成功
					if (listener != null) {
						listener.onSucess(hash); // 回调监听
					}
				} else {
					if (listener != null) {
						listener.onError(info.statusCode, info.error);
					}
				}
			}

		}, new UploadOptions(null, null, false, new UpProgressHandler() {
			public void progress(String key, double percent) {
				if (listener != null) {
					listener.onProgress((int) (percent * 100));
				}
			}
		}, null));

	}

}
