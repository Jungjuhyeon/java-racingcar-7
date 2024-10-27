package racingcar.service;

import racingcar.dto.RacingInput;
import racingcar.dto.RacingResult;
import racingcar.model.Car;
import racingcar.model.RacingRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class RacingService {
    private final RacingRecord racingRecord;

    public RacingService(RacingRecord racingRecord){
        this.racingRecord = racingRecord;
    }

    private List<Car> cars;

    public RacingResult racing(RacingInput racingInput) {

        cars = new ArrayList<>();

        splitCarName(racingInput.getCarNames());
        runRaces(racingInput.getTryCount());

        return new RacingResult(getRacingRecord(),getWinnerNames());
    }

    //라운드별 레이싱 결과 기록
    private void runRaces(int tryCount){
        IntStream.range(0, tryCount)
                .mapToObj(i -> recordSingleRace())
                .forEach(racingRecord::addRoundRecordLog);
    }

    //한 라운드의 레이싱 결과 반환
    public String recordSingleRace() {
        StringBuilder roundRecord = new StringBuilder();
        for (Car racingCar : cars) {
            roundRecord.append(racingCar.forwardBar()).append("\n");
        }

        return roundRecord.toString();
    }

    // (,)기준으로 자르기
    private void splitCarName(String CarNames){
        String[] CarArr = CarNames.split(",");

        for(String carName :CarArr){
            cars.add(new Car(carName));
        }
    }
    private String getWinnerNames(){
        cars.sort((car1,car2) -> car2.getBarLength() - car1.getBarLength());

        List<String> winners = new ArrayList<>();

        int winnerForwardBarLen = cars.getFirst().getBarLength();
        for (Car racingCar : cars) {
            if (racingCar.getBarLength() == winnerForwardBarLen) {
                winners.add(racingCar.getNmae());
            }
        }
        return String.join("," + " ", winners); // 여러 명이면 쉼표로 연결되고, 1명이면 쉼표 없이 이름만 출력됨
    }

    public String getRacingRecord(){
        return racingRecord.getRacingRecord();
    }
}