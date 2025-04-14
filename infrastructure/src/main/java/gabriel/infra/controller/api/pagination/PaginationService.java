package gabriel.infra.controller.api.pagination;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

@Component
public class PaginationService {

    public <T, R> Page<R> generatePage(Supplier<List<T>> resourceSupplier, Function<T, R> resourceMapper, int pageNum, int pageSize) throws InvalidPageNumberException {
        List<T> resourceList = resourceSupplier.get();

        int totalPages = (int) Math.ceil((double) resourceList.size() / pageSize);

        if(pageNum > totalPages){
            throw new InvalidPageNumberException("Page number " + pageNum + " does not exist");
        }

        List<T> pageableResources;
        if(pageNum == totalPages){
            pageableResources = resourceList.subList(pageSize * (pageNum - 1), resourceList.size());
        } else {
            pageableResources = resourceList.subList(pageSize * (pageNum - 1), pageNum * pageSize);
        }

        List<R> content = pageableResources.stream()
                .map(resourceMapper)
                .toList();

        return new Page<>(pageNum, pageSize, totalPages, content);
    }

}
