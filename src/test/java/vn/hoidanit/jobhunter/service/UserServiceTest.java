package vn.hoidanit.jobhunter.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.repository.UserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    @Test
    void updateAUserShouldPersistChanges() {
        UserRepository repo = mock(UserRepository.class);
        UserService service = new UserService(repo);

        User existing = new User();
        existing.setId(1L);
        existing.setEmail("old@ex.com");
        existing.setName("Old");
        existing.setPassword("oldpass");

        when(repo.findById(1L)).thenReturn(Optional.of(existing));
        when(repo.save(existing)).thenReturn(existing);

        User update = new User();
        update.setId(1L);
        update.setEmail("new@ex.com");
        update.setName("New");
        update.setPassword("newpass");

        User result = service.updateAUser(update);

        assertSame(existing, result);
        assertEquals("new@ex.com", existing.getEmail());
        assertEquals("New", existing.getName());
        assertEquals("newpass", existing.getPassword());
        verify(repo).save(existing);
    }
}
