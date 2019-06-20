package ch.cpnv.angrywirds.Providers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import java.util.ArrayList;

import ch.cpnv.angrywirds.Models.Data.Language;
import ch.cpnv.angrywirds.Models.Data.Vocabulary;
import ch.cpnv.angrywirds.Models.Data.Word;

/**
 * Created by Xavier on 07.06.18.
 */

public abstract class VocProvider {
    private static final String API = "http://voxerver.mycpnv.ch/api/v1/";

    public enum Status { unknown, ready, cancelled, nocnx }
    public static Status status = Status.unknown;

    public static ArrayList<Language> languages;
    public static ArrayList<Vocabulary> vocabularies;

    static public void load() {
        languages = new ArrayList<Language>();
        vocabularies = new ArrayList<Vocabulary>();

        HttpRequestBuilder requestLangues = new HttpRequestBuilder();
        Net.HttpRequest httpRequestLangues = requestLangues.newRequest().method(Net.HttpMethods.GET).url(API+"languages").build();
        Gdx.net.sendHttpRequest(httpRequestLangues, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                JsonReader jsonlangue = new JsonReader();
                JsonValue baselangue = jsonlangue.parse(httpResponse.getResultAsString());
                for (JsonValue langages : baselangue.iterator())
                {
                    languages.add(new Language(langages.getInt("id"),langages.getString("lName")));
                }
            }

            @Override
            public void failed(Throwable t) {
                status = Status.nocnx;
                Gdx.app.log("ANGRY", "No connection", t);
            }

            @Override
            public void cancelled() {
                status = Status.cancelled;
                Gdx.app.log("ANGRY", "cancelled");
            }
        });

        httpRequestLangues = requestLangues.newRequest().method(Net.HttpMethods.GET).url(API+"fullvocs").build();
        Gdx.net.sendHttpRequest(httpRequestLangues, new Net.HttpResponseListener() {
            @Override
            public void handleHttpResponse(Net.HttpResponse httpResponse) {
                JsonReader jreader = new JsonReader();
                JsonValue vocs = jreader.parse(httpResponse.getResultAsString());
                for (JsonValue voc : vocs.iterator())
                {
                    Vocabulary newvoc = new Vocabulary(voc.getInt("mId"),voc.getString("mTitle"),voc.getInt("mLang1"),voc.getInt("mLang2"));
                    for (JsonValue word : voc.get("Words").iterator())
                    {
                        newvoc.addWord(new Word(word.getInt("mId"), word.getString("mValue1"), word.getString("mValue2")));
                    }
                    vocabularies.add(newvoc);
                }
                status = Status.ready;
            }

            @Override
            public void failed(Throwable t) {
                status = Status.nocnx;
                Gdx.app.log("ANGRY", "No connection", t);
            }

            @Override
            public void cancelled() {
                status = Status.cancelled;
                Gdx.app.log("ANGRY", "cancelled");
            }
        });

    }

}
