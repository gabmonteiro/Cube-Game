package main.java;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class SongUtil {

    private static Map<String, AudioStream> audios = new HashMap<>();
    private static String KILL_ENEMY_SOUND = "kill-enemy";
    private static String RESPAWN_ENEMY_SOUND = "respawn-enemy";

    public SongUtil() {
        try {
            audios.put(KILL_ENEMY_SOUND, criaAudio("/main/resources/kill-enemy.wav"));
            audios.put(RESPAWN_ENEMY_SOUND, criaAudio("/main/resources/respawn-enemy.wav"));
        } catch (Exception ex) {
            System.err.println("Não foi possível carregar os audios do jogo ");
            ex.printStackTrace();
        }
    }

    public static void playKillEnemy() {
        executaSom(audios.get(KILL_ENEMY_SOUND));
    }

    public static void playRespawnEnemy() {
        executaSom(audios.get(RESPAWN_ENEMY_SOUND));
    }

    private static void executaSom(AudioStream audioStream) {
        AudioPlayer.player.start(audioStream);
    }

    private static AudioStream criaAudio(String caminhoAudio) throws Exception {
        InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream("main/resources/kill-enemy.wav");
        return new AudioStream(in);
    }
}
