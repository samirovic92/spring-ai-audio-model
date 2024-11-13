package com.ai.audiomodel.service;

import org.springframework.ai.audio.transcription.AudioTranscriptionPrompt;
import org.springframework.ai.audio.transcription.AudioTranscriptionResponse;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.ai.openai.OpenAiAudioTranscriptionOptions;
import org.springframework.ai.openai.api.OpenAiAudioApi;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AIAudioService {

    private final OpenAiAudioTranscriptionModel openAiTranscriptionModel;

    public AIAudioService(OpenAiAudioTranscriptionModel openAiTranscriptionModel) {
        this.openAiTranscriptionModel = openAiTranscriptionModel;
    }


    public String transcript(MultipartFile audioFile, String language) {
        var responseFormat = OpenAiAudioApi.TranscriptResponseFormat.TEXT;

        var transcriptionOptions = OpenAiAudioTranscriptionOptions.builder()
                .withLanguage(language)
                .withTemperature(0f)
                .withResponseFormat(responseFormat)
                .build();
        var prompt = new AudioTranscriptionPrompt(audioFile.getResource(), transcriptionOptions);
        return  openAiTranscriptionModel.call(prompt)
                .getResult()
                .getOutput();
    }
}
