import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.MathContext;
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

        SortedMap<BigDecimal,LinkedList<Song>> songs = parseSongData(numSongs,br);
        printTopMSongs(numSongsToSelect, songs);

    }

    private static void printTopMSongs(int numSongsToSelect, SortedMap<BigDecimal, LinkedList<Song>> songs) {
        int foundSongs = 0;
        while(foundSongs < numSongsToSelect){
            BigDecimal highestQuality = songs.lastKey();
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

    private static TreeMap<BigDecimal, LinkedList<Song>> parseSongData(int numSongs, BufferedReader br) {
        TreeMap<BigDecimal,LinkedList<Song>> songList = new TreeMap<>();
        String songLine = null;
        for(int i=1;i<=numSongs;i++){
            try{
                songLine = br.readLine();
            }catch(IOException e){
                System.out.println("Error parsing song data");
            }
            parseSongAndCount(i, songLine, songList);
        }

        return songList;

    }

    private static void parseSongAndCount(int i, String songLine, TreeMap<BigDecimal,LinkedList<Song>> songMap){
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
        private BigDecimal quality;

        public Song(int trackNumber,String songName,Long playCount){
            this.name = songName;
            this.trackNumber = trackNumber;
            this.playCount = playCount;

            this.quality = computeQuality(trackNumber,playCount);

        }

        private BigDecimal computeQuality(int trackNumber, Long playCount) {
            BigDecimal predictedPlayCount = computeZipf(trackNumber);
            return new BigDecimal(Long.toString(playCount)).divide(predictedPlayCount, MathContext.DECIMAL128);
        }

        private BigDecimal computeZipf(int trackNumber) {
            return BigDecimal.ONE.divide(new BigDecimal(trackNumber),MathContext.DECIMAL128);
        }


    }

}
