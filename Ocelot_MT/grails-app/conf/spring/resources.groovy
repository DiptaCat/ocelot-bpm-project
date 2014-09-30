import util.CategoryItemMarshaller
import util.CustomObjectMarshallers
import util.PaletteItemMarshaller
import util.PaletteMarshaller

// Place your Spring DSL code here
beans = {
	customObjectMarshallers( CustomObjectMarshallers ) {
		marshallers = [
				new PaletteItemMarshaller(),
				new PaletteMarshaller(),
				new CategoryItemMarshaller()
		]
	}
}