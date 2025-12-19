package group3.com.demoMymemories.service;

import group3.com.demoMymemories.entity.*;

import group3.com.demoMymemories.repository.MemoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
 
@Service
@RequiredArgsConstructor
public class MemoryService {

    private final MemoryRepository memoryRepository;

    public List<Memory> getAllMemories() {
        return memoryRepository.findAll();
    }

    public Optional<Memory> getMemoryById(Long id) {
        return memoryRepository.findById(id);
    }

    public Memory createMemory(Memory memory) {
        return memoryRepository.save(memory);
    }

    public Memory updateMemory(Long id, Memory updatedMemory) {
        return memoryRepository.findById(id).map(memory -> {
            memory.setTitle(updatedMemory.getTitle());
            memory.setContent(updatedMemory.getContent());
            memory.setTags(updatedMemory.getTags());
            memory.setCategory(updatedMemory.getCategory());
            memory.setUser(updatedMemory.getUser());
            return memoryRepository.save(memory);
        }).orElseThrow(() -> new RuntimeException("Memory not found"));
    }

    public void deleteMemory(Long id) {
        memoryRepository.deleteById(id);
    }
}
