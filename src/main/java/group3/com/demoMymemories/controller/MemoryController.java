package group3.com.demoMymemories.controller;

import group3.com.demoMymemories.entity.*;
import group3.com.demoMymemories.service.MemoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/memories")
@RequiredArgsConstructor
public class MemoryController {

    private final MemoryService memoryService;

    // Get all memories
    @GetMapping
    public List<Memory> getAllMemories() {
        return memoryService.getAllMemories();
    }

    // Get memory by ID
    @GetMapping("/{id}")
    public ResponseEntity<Memory> getMemoryById(@PathVariable Long id) {
        return memoryService.getMemoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create new memory
    @PostMapping
    public Memory createMemory(@RequestBody Memory memory) {
        return memoryService.createMemory(memory);
    }

    // Update memory
    @PutMapping("/{id}")
    public Memory updateMemory(@PathVariable Long id, @RequestBody Memory memory) {
        return memoryService.updateMemory(id, memory);
    }

    // Delete memory
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMemory(@PathVariable Long id) {
        memoryService.deleteMemory(id);
        return ResponseEntity.noContent().build();
    }
}
