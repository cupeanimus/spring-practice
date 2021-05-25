package com.kyle.springpractice.practice.jpa;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@RunWith(SpringRunner.class)
@DataJpaTest
public class StationRespoitoryTest {

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private LineRepository lineRepository;


    @BeforeEach
    void emptyRepository(){
        stationRepository.deleteAll();
    }

    @Test
    public void save() {
        Station expected = new Station("잠실역");
        Station actual = stationRepository.save(expected);

        assertAll(
                () -> assertThat(actual.getId()).isNotNull(),
                () -> assertThat(actual.getName()).isEqualTo(expected.getName())
        );
    }

    @Test
    public void findByNmae() {
        String expected = "서울역";
        stationRepository.save(new Station(expected));
        String actual = stationRepository.findByName(expected).getName();
        assertThat(actual).isEqualTo(expected);

    }

    @Test
    public void identity() {
        Station station1 = stationRepository.save(new Station("사당역"));
        Station station2 = stationRepository.findById(station1.getId()).get();
        assertThat(station1 == station2).isTrue();
    }

    @Test
    public void update() {
        Station station1 = stationRepository.save(new Station("강남역"));
        station1.changeName("몽촌토성역");
        Station station2 = stationRepository.findByName("몽촌토성역");
        assertThat(station2).isNotNull();
    }

    @Test
    public void saveWithLine() {
        Station expected = new Station("잠실역");
        expected.setLine(lineRepository.save(new Line("2호선")));
        Station actual = stationRepository.save(expected);
        stationRepository.flush(); // transaction commit
    }

    @Test
    public void findByNameWithLine() {
        insertData();
        Station actual = stationRepository.findByName("교대역");
        assertThat(actual).isNotNull();
        assertThat(actual.getLine().getName()).isEqualTo("3호선");
    }

    private void insertData() {
        Station expected = new Station("교대역");
        expected.setLine(lineRepository.save(new Line("3호선")));
        stationRepository.save(expected);
    }

    @Test
    public void updateWithLine() {
        insertData();
        Station expected = stationRepository.findByName("교대역");
        expected.setLine(lineRepository.save(new Line("2호선")));
        stationRepository.flush(); // transaction commit
    }

}
