package ru.tagallteam.hackstarter.application.common.filter;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Page<T> {

    @Schema(description = "Номер текущей страницы")
    private int number;

    @Schema(description = "Количество элементов на текущей странице")
    private int numberOfElements;

    @Schema(description = "Является ли данная страница первой")
    private boolean first;

    @Schema(description = "Является ли данная страница последней")
    private boolean last;

    @Schema(description = "Имеется ли следующая страница")
    private boolean hasNext;

    @Schema(description = "Имеется ли предыдущая страница")
    private boolean hasPrevious;

    @Schema(description = "Количество страниц")
    private int totalPages;

    @Schema(description = "Общее количество элементов")
    private long totalElements;

    @Schema(description = "Имеется ли содержимое")
    private boolean hasContent;

    @Schema(description = "Содержимое в виде списка элементов")
    private List<T> content;

    /**
     * Страница.
     *
     * @param page страница.
     * @param <T> параметр.
     * @return страница.
     */
    public static <T> Page<T> of(org.springframework.data.domain.Page<T> page) {
        return Page.<T>builder()
                .number(page.getNumber())
                .numberOfElements(page.getNumberOfElements())
                .first(page.isFirst())
                .last(page.isLast())
                .hasNext(page.hasNext())
                .hasPrevious(page.hasPrevious())
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .hasContent(page.hasContent())
                .content(page.getContent())
                .build();
    }

    /**
     * Формирование страницы.
     *
     * @param <T> параметр.
     * @return страница
     */
    public static <T> Page<T> empty() {
        return Page.<T>builder()
                .number(0)
                .numberOfElements(0)
                .first(true)
                .last(true)
                .hasNext(false)
                .hasPrevious(false)
                .totalPages(0)
                .totalElements(0)
                .hasContent(false)
                .content(Collections.emptyList())
                .build();
    }

    /**
     * Маппа.
     *
     * @param converter конфертер.
     * @param <U> параметр.
     * @return страница.
     */
    public <U> Page<U> map(Function<? super T, ? extends U> converter) {
        List<U> list = content.stream().map(converter).collect(Collectors.toList());
        return Page.<U>builder()
                .number(this.getNumber())
                .numberOfElements(this.getNumberOfElements())
                .first(this.isFirst())
                .last(this.isLast())
                .hasNext(this.isHasNext())
                .hasPrevious(this.isHasPrevious())
                .totalPages(this.getTotalPages())
                .totalElements(this.getTotalElements())
                .hasContent(this.isHasContent())
                .content(list)
                .build();
    }

    /**
     * Фильтр.
     *
     * @param predicate предикат.
     * @return страница.
     */
    public Page<T> filter(Predicate<? super T> predicate) {
        List<T> list = content.stream().filter(predicate).collect(Collectors.toList());
        return Page.<T>builder()
                .number(this.getNumber())
                .numberOfElements(list.size())
                .first(this.isFirst())
                .last(this.isLast())
                .hasNext(this.isHasNext())
                .hasPrevious(this.isHasPrevious())
                .totalPages(this.getTotalPages())
                .totalElements(this.getTotalElements())
                .hasContent(!list.isEmpty())
                .content(list)
                .build();
    }
}
