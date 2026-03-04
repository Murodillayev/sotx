package uz.pdp.sotx.servuce;

import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import uz.pdp.sotx.mapper.TodoMapper;
import uz.pdp.sotx.mockData.MockData;
import uz.pdp.sotx.model.dto.TodoDto;
import uz.pdp.sotx.model.dto.TodoSaveDto;
import uz.pdp.sotx.repository.TodoRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class TodoServiceTest {

    @Mock
    private TodoMapper mapper;

    @Mock
    private TodoRepository repository;

    @InjectMocks
    private TodoService service;

//    @BeforeEach
//    public void setUp() {
//        repository = Mockito.mock(TodoRepository.class);
//        mapper = Mockito.mock(TodoMapper.class);
//        service = new TodoService(mapper, repository);
//    }

    @Test
    void create() {
        when(mapper.fromDto(MockData.VALID_CREATE_DTO)).thenReturn(MockData.VALID_TODO);
        when(repository.save(MockData.VALID_TODO)).thenReturn(MockData.VALID_TODO);
        when(mapper.toDto(MockData.VALID_TODO)).thenReturn(MockData.VALID_DTO);

        TodoSaveDto req = MockData.VALID_CREATE_DTO;
        TodoDto result = service.create(req);
        assertNotNull(result);
        assertEquals(req.getDescription(), result.getDescription());
        assertEquals(req.getTitle(), result.getTitle());

        verify(repository, times(1)).save(MockData.VALID_TODO);
        verify(mapper, times(1)).fromDto(MockData.VALID_CREATE_DTO);
        verify(mapper, times(1)).toDto(MockData.VALID_TODO);
    }

    @Test
    void testCreate_shouldThrowRuntimeExceptionWhenDtoIsNull() {
        when(mapper.fromDto(null)).thenReturn(null);

        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> service.create(null));
        assertEquals("dto is null", runtimeException.getMessage());

        verify(repository, never()).save(Mockito.any());
        verify(mapper, never()).toDto(Mockito.any());
        verify(mapper, times(1)).fromDto(null);
    }

    @Test
    void testCreate_shouldThrowRuntimeExceptionWhenTitleIsNull() {
        when(mapper.fromDto(MockData.TITLE_IS_NULL_CREATE_DTO)).thenReturn(MockData.TITLE_IS_NULL_TODO);

        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> service.create(MockData.TITLE_IS_NULL_CREATE_DTO));
        assertEquals("Title is required", runtimeException.getMessage());

        verify(repository, never()).save(Mockito.any());
        verify(mapper, never()).toDto(Mockito.any());
        verify(mapper, times(1)).fromDto(MockData.TITLE_IS_NULL_CREATE_DTO);
    }
}