package net.shopnc.b2b2c.android.bean;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by wj on 2015/12/24.
 * 文件实体类
 */
public class ImageFile {
        private int file_id; // 字段id
        private String file_name; // 文件名称
        private String origin_file_name; // 原文件名称
        private String file_url; // 图片路径

        public static ImageFile newInstance(String jsonStr) {
            ImageFile imageFile = new ImageFile();

            try {
                JSONObject jsonObject = new JSONObject(jsonStr);
                imageFile.setFile_id(jsonObject.optInt("file_id"));
                imageFile.setFile_name(jsonObject.optString("file_name"));
                imageFile.setFile_url(jsonObject.optString("file_url"));
                imageFile.setOrigin_file_name(jsonObject.optString("origin_file_name"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return imageFile;
        }

        public int getFile_id() {
            return file_id;
        }

        public void setFile_id(int file_id) {
            this.file_id = file_id;
        }

        public String getFile_name() {
            return file_name;
        }

        public void setFile_name(String file_name) {
            this.file_name = file_name;
        }

        public String getOrigin_file_name() {
            return origin_file_name;
        }

        public void setOrigin_file_name(String origin_file_name) {
            this.origin_file_name = origin_file_name;
        }

        public String getFile_url() {
            return file_url;
        }

        public void setFile_url(String file_url) {
            this.file_url = file_url;
        }

        @Override
        public String toString() {
            return "ImageFile{" +
                    "file_id=" + file_id +
                    ", file_name='" + file_name + '\'' +
                    ", origin_file_name='" + origin_file_name + '\'' +
                    ", file_url='" + file_url + '\'' +
                    '}';
        }
}
