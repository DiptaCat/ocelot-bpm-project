import util.CategoryItemMarshaller
import util.CustomObjectMarshallers
import util.CustomPaletteItemMarshaller
import util.PaletteItemMarshaller
import util.PaletteMarshaller
import util.FormDataMarshaller

// Place your Spring DSL code here
beans = {
	customObjectMarshallers(CustomObjectMarshallers) {
		marshallers = [
				new PaletteItemMarshaller(),
                new CustomPaletteItemMarshaller(),
				new PaletteMarshaller(),
				new CategoryItemMarshaller(),
				new FormDataMarshaller()
		]
	}
}