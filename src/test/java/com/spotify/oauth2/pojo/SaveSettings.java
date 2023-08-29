package com.spotify.oauth2.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SaveSettings {
    @JsonProperty("storeName")
    private String storeName;
    @JsonProperty("userName")
    private Object userName;
    @JsonProperty("password")
    private String password;
    @JsonProperty("failureAttempt")
    private Integer failureAttempt;
    @JsonProperty("isEnabled")
    private Boolean isEnabled;
    @JsonProperty("isExpired")
    private Boolean isExpired;
    @JsonProperty("isDeleted")
    private Boolean isDeleted;
    @JsonProperty("createdOn")
    private String createdOn;
    @JsonProperty("modifiedOn")
    private String modifiedOn;
    @JsonProperty("lastscheduledDate")
    private String lastscheduledDate;
    @JsonProperty("internalExtraData")
    private Object internalExtraData;
    @JsonProperty("portalProfileId")
    private Object portalProfileId;
    @JsonProperty("storeAccountId")
    private Integer storeAccountId;
    @JsonProperty("profileId")
    private Integer profileId;
    @JsonProperty("subscriberId")
    private Integer subscriberId;
    @JsonProperty("integrationTypeId")
    private Integer integrationTypeId;
    @JsonProperty("scheduleDuration")
    private Integer scheduleDuration;
    @JsonProperty("channelType")
    private Integer channelType;
    @JsonProperty("consumerType")
    private Integer consumerType;
    @JsonProperty("AccessToken")
    private String accessToken;
    @JsonProperty("CopyCriteria")
    private Integer copyCriteria;
    @JsonProperty("ConnectionURL")
    private String connectionURL;
    @JsonProperty("DownloadStartDate")
    private String downloadStartDate;

}
