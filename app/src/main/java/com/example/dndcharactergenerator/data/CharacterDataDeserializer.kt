package com.example.dndcharactergenerator.data

import com.google.gson.*
import java.lang.reflect.Type


class CharacterDataDeserializer : JsonDeserializer<CharacterData> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): CharacterData {
        val jsonObject = json!!.asJsonObject

        val firstName = jsonObject.get("firstName").asString
        val lastName = jsonObject.get("lastName").asString
        val race = Race.fromString(jsonObject.get("raceName").asString)
        val raceName = jsonObject.get("raceName").asString
        val age = jsonObject.get("age").asInt
        val characteristic = Gson().fromJson(jsonObject.get("characteristic"), Characteristic::class.java)

        return CharacterData(firstName, lastName, race, raceName, age, characteristic)
    }
}

class CharacterDataSerializer : JsonSerializer<CharacterData> {
    override fun serialize(
        src: CharacterData?,
        typeOfSrc: Type?,
        context: JsonSerializationContext?
    ): JsonElement {
        val jsonObject = JsonObject()
        try {
            jsonObject.add("firstName", context?.serialize(src!!.firstName))
            jsonObject.add("lastName", context?.serialize(src!!.lastName))
            jsonObject.add("raceName", context?.serialize(src!!.raceName))
            jsonObject.add("age", context?.serialize(src!!.age))
            jsonObject.add(
                "characteristic",
                JsonParser().parse(Gson().toJson(src?.characteristic)).asJsonObject
            )
        } catch (e: Exception) {
            print(e.message)
        }
        return jsonObject
    }

}
