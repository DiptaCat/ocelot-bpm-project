package ocelot

class CategoryItem {
	String name

	static hasMany = [paletteItems: PaletteItem, customPaletteItems: CustomPaletteItem]

	static constraints = {
	}
}
