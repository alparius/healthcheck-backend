package healthcheck.application.mapper;

public interface MapperInterface <Model, Dto> {
    Model convertDtoToModel(Dto dto);
    Dto convertModelToDto(Model model);
}
