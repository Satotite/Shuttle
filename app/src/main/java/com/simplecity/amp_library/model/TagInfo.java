import org.jaudiotagger.tag.TagException;
Add comment
More actions



/**


 * A holder for various ID3 tag information associated with a file.

 */

public class TagInfo implements Serializable {



    private static final long serialVersionUID = 1L;


    private static final String UNKNOWN = "Unknown";



    private String artistName;


    private String albumArtistName;


    private String albumName;


    private String trackName;


    private int trackNumber;


    private int trackTotal;


    private int discNumber;


    private int discTotal;


    private String bitrate;


    private String format;


    private int sampleRate;


    private String genre;



    public TagInfo(String filePath) {


        if (filePath == null) return;


        File file = new File(filePath);


        if (!file.exists()) return;


        try {


            AudioFile audioFile = AudioFileIO.read(file);


            this.artistName      = getTag(audioFile, FieldKey.ARTIST);


            this.albumArtistName = getTag(audioFile, FieldKey.ALBUM_ARTIST);


            this.albumName       = getTag(audioFile, FieldKey.ALBUM);


            this.trackName       = getTag(audioFile, FieldKey.TITLE);


            this.trackNumber     = StringUtils.parseInt(getTag(audioFile, FieldKey.TRACK));


            this.trackTotal      = StringUtils.parseInt(getTag(audioFile, FieldKey.TRACK_TOTAL));


            this.discNumber      = StringUtils.parseInt(getTag(audioFile, FieldKey.DISC_NO));


            this.discTotal       = StringUtils.parseInt(getTag(audioFile, FieldKey.DISC_TOTAL));


            this.bitrate         = getBitrate(audioFile);


            this.format          = getFormat(audioFile);


            this.sampleRate      = getSampleRate(audioFile);


            this.genre           = getTag(audioFile, FieldKey.GENRE);


        } catch (CannotReadException | IOException | TagException |


                 ReadOnlyFileException | InvalidAudioFrameException e) {


            e.printStackTrace();






        }

    }




    /**


     * Retrieves a tag value, or UNKNOWN if not available.


     */


    private String getTag(AudioFile audioFile, FieldKey key) {

        try {

            if (audioFile != null) {

                Tag tag = audioFile.getTag();

@@ -75,41 +74,104 @@ public String getTag(AudioFile audioFile, FieldKey key) {

                    }

                }

            }


        } catch (UnsupportedOperationException e) {


            // Some audio formats may not support this tag

        }


        return UNKNOWN;

    }




    /**


     * Retrieves bitrate, or UNKNOWN if not available.


     */

    public static String getBitrate(AudioFile audioFile) {

        try {

            if (audioFile != null) {


                AudioHeader header = audioFile.getAudioHeader();


                return header.getBitRate();

            }


        } catch (UnsupportedOperationException e) {


            // Bitrate not supported for this format

        }


        return UNKNOWN;

    }




    /**


     * Retrieves format string, or UNKNOWN if not available.


     */

    public static String getFormat(AudioFile audioFile) {

        try {

            if (audioFile != null) {


                AudioHeader header = audioFile.getAudioHeader();


                return header.getFormat();

            }


        } catch (UnsupportedOperationException e) {


            // Format not supported for this file

        }


        return UNKNOWN;

    }




    /**


     * Retrieves sample rate as number, or -1 if not available.


     */

    public static int getSampleRate(AudioFile audioFile) {

        try {

            if (audioFile != null) {


                AudioHeader header = audioFile.getAudioHeader();


                return header.getSampleRateAsNumber();

            }


        } catch (UnsupportedOperationException e) {


            // Sample rate not supported for this format

        }

        return -1;

    }





    // --- Accessors ---





    public String getArtistName() {


        return artistName;


    }





    public String getAlbumArtistName() {


        return albumArtistName;


    }





    public String getAlbumName() {


        return albumName;


    }





    public String getTrackName() {


        return trackName;


    }





    public int getTrackNumber() {


        return trackNumber;


    }





    public int getTrackTotal() {


        return trackTotal;


    }





    public int getDiscNumber() {


        return discNumber;


    }





    public int getDiscTotal() {


        return discTotal;


    }





    public String getBitrateLabel() {


        return bitrate;


    }





    public String getFormatLabel() {


        return format;


    }





    public int getSampleRate() {


        return sampleRate;


    }





    public String getGenre() {


        return genre;


    }

}