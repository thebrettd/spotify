import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: brett
 * Date: 6/22/13
 * Time: 2:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class ZipfSong {

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int numSongs = 0;
        int numSongsToSelect = 0;
        try{
            String line = br.readLine();
            StringTokenizer t = new StringTokenizer(line);

            String n = t.nextToken(" ");
            String m = t.nextToken(" ");

            numSongs = Integer.parseInt(n);
            numSongsToSelect = Integer.parseInt(m);
        }catch(IOException e){
            System.out.println("Error reading input from stdin");
        }

        SortedMap<Float,LinkedList<Song>> songs = parseSongData(numSongs,br);
        printTopMSongs(numSongsToSelect, songs);

    }

    private static void printTopMSongs(int numSongsToSelect, SortedMap<Float, LinkedList<Song>> songs) {
        int foundSongs = 0;
        while(foundSongs < numSongsToSelect){
            Float highestQuality = songs.lastKey();
            List<Song> highestQualitySongs = songs.get(highestQuality);
            for(Song song : highestQualitySongs){
                if (!(foundSongs == numSongsToSelect)){
                        if (foundSongs < numSongsToSelect-1){
                            System.out.println(song.name);
                        }else{
                            System.out.print(song.name);
                        }
                        foundSongs++;
                }
             }
             songs = songs.headMap(highestQuality);
        }
    }

    private static TreeMap<Float, LinkedList<Song>> parseSongData(int numSongs, BufferedReader br) {
        TreeMap<Float,LinkedList<Song>> songList = new TreeMap<>();
        String songLine = null;
        for(int i=0;i<numSongs;i++){
            try{
                songLine = br.readLine();
            }catch(IOException e){
                System.out.println("Error parsing song data");
            }
            parseSongAndCount(i, songLine, songList);
        }

        return songList;

    }

    private static void parseSongAndCount(int i, String songLine, TreeMap<Float,LinkedList<Song>> songMap){
        StringTokenizer t = new StringTokenizer(songLine, " ");
        Long songCount = Long.parseLong(t.nextToken());
        String songName = t.nextToken();
        Song currSong = new Song(i,songName,songCount);
        if(songMap.get(currSong.quality) == null){
            LinkedList<Song> songList = new LinkedList<>();
            songList.add(currSong);
            songMap.put(currSong.quality,songList);
        }else{
            songMap.get(currSong.quality).add(currSong);
        }
    }

    private static class Song{
        private String name;
        private Integer trackNumber;
        private Long playCount;
        private Float quality;

        public Song(int trackNumber,String songName,Long playCount){
            this.name = songName;
            this.trackNumber = trackNumber;
            this.playCount = playCount;

            this.quality = computeQuality(trackNumber,playCount);

        }

        private Float computeQuality(int trackNumber, Long playCount) {
            Float predictedPlayCount = computeZipf(trackNumber);
            return playCount / predictedPlayCount;
        }

        private Float computeZipf(int trackNumber) {
            return Float.parseFloat("1.0") / Float.parseFloat(Integer.toString(trackNumber));
        }


    }

}
