package com.max.news.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 */

public class ChannelTitle {

        @SerializedName("channelId")
        @Expose
        private String channelId;
        @SerializedName("name")
        @Expose
        private String name;

        /**
         *
         * @return
         * The channelId
         */
        public String getChannelId() {
            return channelId;
        }

        /**
         *
         * @param channelId
         * The channelId
         */
        public void setChannelId(String channelId) {
            this.channelId = channelId;
        }

        /**
         *
         * @return
         * The name
         */
        public String getName() {
            return name;
        }

        /**
         *
         * @param name
         * The name
         */
        public void setName(String name) {
            this.name = name;
        }

    }
