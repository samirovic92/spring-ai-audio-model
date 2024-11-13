package com.ai.audiomodel.controller;

import com.ai.audiomodel.service.AIAudioService;
import org.springframework.ai.audio.transcription.AudioTranscriptionResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/ai")
public class AIAudioController {

    private final AIAudioService audioService;

    public AIAudioController(AIAudioService audioService) {
        this.audioService = audioService;
    }

    @PostMapping(value = "/transcript", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String transcript(@RequestPart("audioFile") MultipartFile audioFile, @RequestParam String language) {
        return audioService.transcript(audioFile, language);
    }
}
