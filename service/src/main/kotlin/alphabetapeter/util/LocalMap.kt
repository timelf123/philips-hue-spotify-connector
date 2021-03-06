package alphabetapeter.util

import alphabetapeter.model.ColorSet
import alphabetapeter.model.PlayStatus
import io.vertx.core.Vertx
import io.vertx.core.json.JsonObject
import io.vertx.core.shareddata.LocalMap

class LocalMap(private val vertx: Vertx) {

	enum class Key {
		SPOTIFY_ACCESS_TOKEN_KEY,
		SPOTIFY_REFRESH_TOKEN,
		SPOTIFY_STATUS_KEY,
		SPOTIFY_CLIENT_ID,
		SPOTIFY_CLIENT_SECRET,
		SPOTIFY_ALBUM_URL,
		PHILIPS_HUE_COLOR,
		PHILIPS_HUE_USERNAME,
		PHILIPS_HUE_BRIDGE_IP,
		LIGHT_MATCHING,
	}

	private fun localStringMap(): LocalMap<String, String> {
		return vertx.sharedData().getLocalMap<String, String>("string_data")
	}

	private fun localBooleanMap(): LocalMap<String, Boolean> {
		return vertx.sharedData().getLocalMap<String, Boolean>("boolean_data")
	}

	private fun localJsonObjectMap(): LocalMap<String, JsonObject> {
		return vertx.sharedData().getLocalMap<String, JsonObject>("jsonobject_data")
	}

	private fun localHueColorMap(): LocalMap<String, ColorSet> {
		return vertx.sharedData().getLocalMap<String, ColorSet>("color_data")
	}

	fun putString(key: String, value: String) {
		localStringMap()[key] = value
	}

	fun putBoolean(key: String, value: Boolean) {
		localBooleanMap()[key] = value
	}

	fun putSpotifyAccessToken(accessToken: String) {
		localStringMap()[Key.SPOTIFY_ACCESS_TOKEN_KEY.name] = accessToken
	}

	fun getSpotifyAccessToken(): String? {
		return localStringMap()[Key.SPOTIFY_ACCESS_TOKEN_KEY.name]
	}

	fun putSpotifyRefreshToken(refreshToken: String) {
		localStringMap()[Key.SPOTIFY_REFRESH_TOKEN.name] = refreshToken
	}

	fun getSpotifyRefreshToken(): String? {
		return localStringMap()[Key.SPOTIFY_REFRESH_TOKEN.name]
	}

	fun putHueUserName(username: String) {
		localStringMap()[Key.PHILIPS_HUE_USERNAME.name] = username
	}

	fun getHueUsername(): String? {
		return localStringMap()[Key.PHILIPS_HUE_USERNAME.name]
	}

	fun putHueBridgeIp(ip: String) {
		localStringMap()[Key.PHILIPS_HUE_BRIDGE_IP.name] = ip
	}

	fun getHueBridgeIp(): String? {
		return localStringMap()[Key.PHILIPS_HUE_BRIDGE_IP.name]
	}

	fun getSpotifyClientId(): String? {
		return localStringMap()[Key.SPOTIFY_CLIENT_ID.name]
	}

	fun putSpotifyClientId(clientId: String) {
		localStringMap()[Key.SPOTIFY_CLIENT_ID.name] = clientId
	}

	fun getSpotifyClientSecret(): String? {
		return localStringMap()[Key.SPOTIFY_CLIENT_SECRET.name]
	}

	fun putSpotifyClientSecret(secret: String) {
		localStringMap()[Key.SPOTIFY_CLIENT_SECRET.name] = secret
	}

	fun putLightMatchingEnabled(match: Boolean) {
		localBooleanMap()[Key.LIGHT_MATCHING.name] = match
	}

	fun getLightMatchingEnabled(): Boolean {
		return localBooleanMap().getOrDefault(Key.LIGHT_MATCHING.name, true)
	}

	fun putSpotifyStatus(status: PlayStatus) {
		localJsonObjectMap()[Key.SPOTIFY_STATUS_KEY.name] = status.toJsonObject()
	}

	fun getSpotifyStatus(): PlayStatus {
		return PlayStatus.fromJsonObject(localJsonObjectMap().getOrDefault(Key.SPOTIFY_STATUS_KEY.name, JsonObject()))
	}

}