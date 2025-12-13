package week6.day2.code;

import com.github.victools.jsonschema.generator.OptionPreset;
import com.github.victools.jsonschema.generator.SchemaGenerator;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfig;
import com.github.victools.jsonschema.generator.SchemaGeneratorConfigBuilder;
import com.github.victools.jsonschema.generator.SchemaVersion;

import week6.day2.fakerest.api.response.pojos.GetActivity;

public class FakerAPISchemaGenerator {

	public static void main(String[] args) {
		SchemaGeneratorConfig config = new 
				SchemaGeneratorConfigBuilder(SchemaVersion.DRAFT_7, OptionPreset.PLAIN_JSON).build();
		SchemaGenerator schemaGenerator = new SchemaGenerator(config);
		System.out.println(schemaGenerator.generateSchema(GetActivity[].class).toPrettyString());
	}

}