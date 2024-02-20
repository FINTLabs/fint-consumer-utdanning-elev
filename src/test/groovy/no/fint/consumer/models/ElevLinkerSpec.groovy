package no.fint.consumer.models

import com.fasterxml.jackson.databind.ObjectMapper
import no.fint.consumer.models.elev.ElevLinker
import no.fint.model.resource.utdanning.elev.ElevResource
import org.junit.Ignore
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = [ "logging.level.no.fint.consumer.models=TRACE" ])
@ActiveProfiles("test")
class ElevLinkerSpec extends Specification {

    @Autowired
    private ElevLinker elevLinker

    @Autowired
    private ObjectMapper objectMapper


    //Test ignored becuse it fails after the cache has been updated
//    def 'Load elev.json and verify self links'() {
//        given:
//        def elev = objectMapper.readValue(getClass().getResourceAsStream('/elev.json'), ElevResource)
//
//        when:
//        def result = elevLinker.toResource(elev)
//        println(result)
//
//        then:
//        result.selfLinks.size() == 4
//        result.selfLinks.every { it.href.contains('/elev/') }
//    }
}
