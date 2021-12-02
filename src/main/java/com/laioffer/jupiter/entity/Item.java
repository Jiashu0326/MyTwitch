package com.laioffer.jupiter.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonDeserialize(builder = Item.Builder.class)
public class Item {//不同类型的数据类型
    @JsonProperty("id")
    private final String id;

    @JsonProperty("title")
    private final String title;

    @JsonProperty("thumbnail_url")
    private final String thumbnailUrl;//

    @JsonProperty("broadcaster_name")//为什么后面的数据不用final？因为他们还有修改的空间（不是直接从twitch返回的）有可能需要自己来set
    @JsonAlias({ "user_name" })//传来的数据中，无论username和broadcastername都当做统一的这个数据
    private String broadcasterName;//直播的人名字

    @JsonProperty("url")
    private String url;

    @JsonProperty("game_id")
    private String gameId;

    @JsonProperty("item_type")
    private ItemType type;//三选一的类型

    private Item(Builder builder) {//构造函数是个private。 如果想调用item的构造函数去初始化一个item对象的话，builder.build方法必须要能调用这个构造函数才行
        this.id = builder.id;
        this.title = builder.title;
        this.url = builder.url;
        this.thumbnailUrl = builder.thumbnailUrl;
        this.broadcasterName = builder.broadcasterName;
        this.gameId = builder.gameId;
        this.type = builder.type;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public Item setUrl(String url) {//为什么要用setter？
        this.url = url;
        return this;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
    public String getBroadcasterName() {
        return broadcasterName;
    }

    public String getGameId() {
        return gameId;
    }

    public Item setGameId(String gameId) {
        this.gameId = gameId;
        return this;
    }

    public ItemType getType() {
        return type;
    }

    public Item setType(ItemType type) {
        this.type = type;
        return this;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Builder {//为什么要static？
        @JsonProperty("id")
        private String id;

        @JsonProperty("title")
        private String title;

        @JsonProperty("url")
        private String url;

        @JsonProperty("thumbnail_url")
        private String thumbnailUrl;

        @JsonProperty("broadcaster_name")
        @JsonAlias({ "user_name" })
        private String broadcasterName;

        @JsonProperty("game_id")
        private String gameId;

        @JsonProperty("item_type")
        private ItemType type;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder thumbnailUrl(String thumbnailUrl) {
            this.thumbnailUrl = thumbnailUrl;
            return this;
        }

        public Builder broadcasterName(String broadcasterName) {
            this.broadcasterName = broadcasterName;
            return this;
        }

        public Builder gameId(String gameId) {
            this.gameId = gameId;
            return this;
        }

        public Builder type(ItemType type) {
            this.type = type;
            return this;
        }

        public Item build() {
            return new Item(this);
        }//在item里面。可以调用builder
    }
}