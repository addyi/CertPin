package de.eosn.certpin

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GitHubUser(

    @SerializedName("login")
    @Expose
    var login: String? = null,

    @SerializedName("avatar_url")
    @Expose
    var avatarUrl: String? = null,

    @SerializedName("url")
    @Expose
    var url: String? = null,

    @SerializedName("html_url")
    @Expose
    var htmlUrl: String? = null,

    @SerializedName("type")
    @Expose
    var type: String? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("company")
    @Expose
    var company: String? = null,

    @SerializedName("location")
    @Expose
    var location: String? = null,

    @SerializedName("email")
    @Expose
    var email: Any? = null,

    @SerializedName("hireable")
    @Expose
    var hireable: Any? = null,

    @SerializedName("bio")
    @Expose
    var bio: Any? = null,

    @SerializedName("public_repos")
    @Expose
    var publicRepos: Int? = null,

    @SerializedName("public_gists")
    @Expose
    var publicGists: Int? = null,

    @SerializedName("followers")
    @Expose
    var followers: Int? = null,

    @SerializedName("following")
    @Expose
    var following: Int? = null,

    @SerializedName("created_at")
    @Expose
    var createdAt: String? = null,

    @SerializedName("updated_at")
    @Expose
    var updatedAt: String? = null
)