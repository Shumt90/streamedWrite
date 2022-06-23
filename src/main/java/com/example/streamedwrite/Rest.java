package com.example.streamedwrite;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@RestController
public class Rest {

  @GetMapping("oom")
  public ResponseEntity<StreamingResponseBody> streamingResponseBodyResponseEntity() {

    var stream = Stream.generate(() -> "*O_O_M*");

    StreamingResponseBody responseBody = (response) -> {

      stream.forEach(el ->
          {
            try {
              response.write(el.getBytes(StandardCharsets.UTF_8));
            } catch (IOException e) {
              new RuntimeException();
            }finally {
              try {
                response.close();
              } catch (IOException e) {
                new RuntimeException();
              }
            }
          }
      );

    };

    return ResponseEntity.ok(responseBody);

  }
}
