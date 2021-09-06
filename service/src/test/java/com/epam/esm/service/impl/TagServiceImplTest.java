package com.epam.esm.service.impl;

import com.epam.esm.dao.api.TagDao;
import com.epam.esm.entity.Tag;
import com.epam.esm.service.TagService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TagServiceImplTest {
    private static Tag tag = new Tag(1, "SomeTag");
    private TagService service;
    @Mock
    private TagDao dao;

    @BeforeAll
    public void init() {
        MockitoAnnotations.initMocks(this);
        service = new TagServiceImpl(dao);
    }

    @Test
    void findById() {
        Mockito.when(dao.findById(1)).thenReturn(Optional.of(tag));
        Tag actual = service.findById(1);
        assertEquals(tag, actual);
    }

    @Test
    void findAll() {
        List<Tag> expected = new ArrayList<>();
        expected.add(tag);
        Mockito.when(dao.findAll(0, 10)).thenReturn(expected);
        List<Tag> actual = service.findAll(0, 10);
        assertEquals(expected, actual);
    }
}