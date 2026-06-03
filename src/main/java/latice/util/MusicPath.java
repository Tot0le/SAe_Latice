package latice.util;

public enum MusicPath {
	MUSIC_MENU("/musics/main_menu_music.mp3"),
	MUSIC_SETTINGS("/musics/settings_music.mp3"),
	MUSIC_INGAME("/musics/inGame_music.mp3");

	private String musicPath ;
	
	MusicPath(String path) {
		this.musicPath = path;
	}

	public String getPath() {
		return this.musicPath;
	}
	
	public static MusicPath getMusicPath(String nameMusicPath) {
		MusicPath returnMusic = null;
		for(MusicPath currentMusicPath : MusicPath.values()) {
			if(currentMusicPath.name().equals(nameMusicPath)) {
				returnMusic = currentMusicPath;
				return returnMusic;
			}
		}
		return returnMusic;
	}
}
