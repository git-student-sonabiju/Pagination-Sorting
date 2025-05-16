package com.edstem.pagination_sorting.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SliceResponse<T> {
	private List<T> content;
	private boolean hasNext;
}
