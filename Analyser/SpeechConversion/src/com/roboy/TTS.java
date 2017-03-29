package com.roboy;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

/**
 * Created by Bratik on 09.02.2017.
 */
public class TTS
{
    private static final String VOICENAME = "kevin";
    private String text;

    public TTS()
    {
        this.text = "wipe you nose and shut your mouth.";
    }
    public void speak(String text) {
        Voice voice;
        VoiceManager voiceManager = VoiceManager.getInstance();
        voice = voiceManager.getVoice(VOICENAME);
        voice.allocate();
        voice.speak(text);
    }

    public void speak()
    {
        Voice voice;
        VoiceManager voiceManager = VoiceManager.getInstance();
        voice = voiceManager.getVoice(VOICENAME);
        voice.allocate();
        voice.speak(this.text);
    }
}



