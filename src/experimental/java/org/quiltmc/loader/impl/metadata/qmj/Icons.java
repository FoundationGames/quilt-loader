package org.quiltmc.loader.impl.metadata.qmj;

import java.util.SortedMap;

import org.jetbrains.annotations.Nullable;

/**
 * Implementation of an icon lookup.
 */
interface Icons {
	@Nullable
	String getIcon(int size);

	/**
	 * Implementation for a mod.
	 */
	final class Single implements Icons {
		@Nullable
		private final String icon;

		Single(@Nullable String icon) {
			this.icon = icon;
		}

		@Nullable
		@Override
		public String getIcon(int size) {
			return this.icon;
		}
	}

	/**
	 * Implementation for a mod which has multiple icons of different sizes.
	 */
	final class Multiple implements Icons {
		private final SortedMap<Integer, String> icons;

		Multiple(SortedMap<Integer, String> icons) {
			this.icons = icons;
		}

		@Nullable
		@Override
		public String getIcon(int size) {
			int iconValue = -1;

			for (int entrySize : this.icons.keySet()) {
				iconValue = entrySize;

				if (iconValue >= size) {
					break;
				}
			}

			return this.icons.get(iconValue);
		}
	}
}