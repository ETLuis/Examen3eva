import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.message.MessageCreateEvent;
import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import discord4j.core.spec.EmbedCreateSpec;
import discord4j.core.spec.MessageCreateSpec;

import javax.imageio.stream.FileImageInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.Instant;

public class main {
    public static void main(String[] args) {

        /*
          Creamos 3 constantes:
          -Una llamada token que es donde guarda el token del bot de Discord
          -Una llamada cliente para el discord
          -Una llamada gateway para podernos logear en el discord
         */

        final String token = args[0];
        final DiscordClient client = DiscordClient.create(token);
        final GatewayDiscordClient gateway = client.login().block();

    /*
      Aqui hacemos el embed donde nos da el titulo y la imagen del mismo.
     */

        EmbedCreateSpec embed = EmbedCreateSpec.builder()
                .title("Pocoyo")
                .image("attachment://loki.jpg")
                .build();

        gateway.on(MessageCreateEvent.class).subscribe(event -> {
            final Message message = event.getMessage();

        /*
          Aqui hacemos el !ping donde si nosotros enviamos !ping el bot nos devolverá el !Pong
         */

            if ("!ping".equals(message.getContent())) {
                final MessageChannel channel = message.getChannel().block();
                channel.createMessage("Pong!").block();
            }


        /*
        Aqui está el funcionamiento del !embed que es muy parecido al !ping y Pong! solo que nos devuelve un embed
        en vez de un mensaje.
         */

                    if ("!embed".equals(message.getContent())) {
                        String IMAGE_URL = "https://c.tenor.com/SLzgDKpTvAoAAAAC/game-day.gif";
                        String ANY_URL = "https://www.youtube.com/watch?v=0HVI9Zr3FgY";
                        final MessageChannel channel = message.getChannel().block();
                        EmbedCreateSpec.Builder builder = EmbedCreateSpec.builder();
                        builder.author("DiscordGIF", ANY_URL, IMAGE_URL);
                        builder.image(IMAGE_URL);
                        builder.title("DiscordGIF");
                        builder.url(ANY_URL);
                        builder.description("Es un gif multicolor del logo de discord");
                        builder.thumbnail(IMAGE_URL);
                        builder.footer("GIF", IMAGE_URL);
                        builder.timestamp(Instant.now());
                        channel.createMessage(builder.build()).block();
                    }

        /*
          Si escribimos "!img" el bot mandará una imágen y está en un try catch por si no encuentra la imagen
          (FileNotFoundException).
         */


            if ("!img".equals(message.getContent())) {

                final MessageChannel channel = message.getChannel().block();


                InputStream fileAsInputStream = null;
                try {
                    fileAsInputStream = new FileInputStream("/home/dam1/IdeaProjects/ImagenGIPHY/src/pocoyo.jpeg");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }


                channel.createMessage(MessageCreateSpec.builder()
                        .content("Viva pocoyo")
                        .addFile("/home/dam1/IdeaProjects/ImagenGIPHY/src/pocoyo.jpeg", fileAsInputStream)
                        .addEmbed(embed)
                        .build()).subscribe();
            }

            if ("/pdf".equals(message.getContent())) {
                //Ya configuré el comando /pdf
                final MessageChannel channel = message.getChannel().block();

                InputStream fileAsInputStream = null;
                try {
                    //Este es el documento pdf.
                    fileAsInputStream = new FileInputStream("/home/dam1/Escritorio/googleDoc.pdf");
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                channel.createMessage(MessageCreateSpec.builder()
                        .addFile("googleDoc.pdf", fileAsInputStream)
                        .build()).subscribe();
            }
        });

        gateway.onDisconnect().block();

    }
}