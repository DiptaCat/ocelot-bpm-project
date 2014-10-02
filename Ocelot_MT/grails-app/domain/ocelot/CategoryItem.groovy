package ocelot

class CategoryItem {
	String name

	static hasMany = [paletteItems: PaletteItem]

	static constraints = {
	}
}
