package week6.day1;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.victools.jsonschema.generator.OptionPreset;
import com.github.victools.jsonschema.generator.SchemaGenerator;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfig;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfigBuilder;
import com.github.victools.jsonschema.generator.SchemaVersion;

import week4.day2.sample.response.pojos.CategoryHardware;

public class SchemaGeneratorDemo {

	public static void main(String[] args) {
		SchemaGeneratorConfigBuilder configBuilder = new SchemaGeneratorConfigBuilder(SchemaVersion.DRAFT_7, OptionPreset.PLAIN_JSON);
	    SchemaGeneratorConfig config = configBuilder.build(); 
	    SchemaGenerator schemaGenerator = new SchemaGenerator(config);
	    JsonNode jsonSchema = schemaGenerator.generateSchema(CategoryHardware.class);
	    System.out.println(jsonSchema.toPrettyString());
	}

}