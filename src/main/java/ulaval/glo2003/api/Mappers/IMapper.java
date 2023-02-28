package ulaval.glo2003.api.Mappers;

public interface IMapper<Entity, Response, Request> {
    Response mapEntitytoResponse(Entity entity);
    Entity mapResponsetoEntity(Request request);
}
