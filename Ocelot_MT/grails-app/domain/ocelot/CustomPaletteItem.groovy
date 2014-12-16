package ocelot

import palette.APaletteItem

class CustomPaletteItem extends APaletteItem {

	boolean activated //TODO think about boolean activated

	static belongsTo = [palette: Palette, category: CategoryItem]
//	static hasMany = [palettes: Palette]

	static constraints = {
		props nullable: true, maxSize: 10000
		svg maxSize: 10000
	}
}
