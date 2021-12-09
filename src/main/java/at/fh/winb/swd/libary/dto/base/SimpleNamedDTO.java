package at.fh.winb.swd.libary.dto.base;

public class SimpleNamedDTO {

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public static SimpleNamedDTO with(final Long id, final String... names) {
        final SimpleNamedDTO simpleNamedDTO = new SimpleNamedDTO();
        simpleNamedDTO.setId(id);
        simpleNamedDTO.setName(String.join(" ", names));
        return simpleNamedDTO;
    }

}
