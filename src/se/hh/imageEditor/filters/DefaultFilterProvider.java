package se.hh.imageEditor.filters;

import static java.util.Arrays.asList;

import java.util.List;

import se.hh.filterApi.Filter;
import se.hh.filterApi.filters.FlipX;
import se.hh.filterApi.filters.Grayscale;
import se.hh.filterApi.filters.Red;
import se.hh.filterApi.filters.Swirl;

public final class DefaultFilterProvider implements FilterProvider {

	@Override
	public List<Filter> getFilters() {
		return asList(
					new Swirl(), 
					new Grayscale(), 
					new FlipX(), 
					new Red()
				);
	}

}
