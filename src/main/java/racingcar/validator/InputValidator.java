package racingcar.validator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class InputValidator {
    private static final int MAX_NAME_LENGTH = 5;

    public static void validateCarNames(List<String> carNames) {
        if (carNames.stream().anyMatch(InputValidator::isInvalidName)) {
            throw new IllegalArgumentException("자동차 이름은 1자 이상 5자 이하로 입력해 주세요.");
        }
    }

    private static boolean isInvalidName(String name) {
        return name.isEmpty() || name.length() > MAX_NAME_LENGTH;
    }

    public static void validateTryCounts(int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("횟수는 1 이상의 정수로 입력해 주세요.");
        }
    }

    public static void checkDuplicateNames(List<String> carNames) {
        Set<String> nameSet = new HashSet<>(carNames);
        if (nameSet.size() != carNames.size()) {
            throw new IllegalArgumentException("자동차 이름은 중복될 수 없습니다.");
        }
    }

    public static void containsInvalidCharacters(List<String> carNames) {
        if(carNames.stream().anyMatch(InputValidator::containsInvalidCharacters)) {
            throw new IllegalArgumentException("자동차 이름은 한글, 영어, 숫자 외의 글자를 포함할 수 없습니다.");
        }
    }


    // 유효하지 않은 문자 포함 여부 체크
    private static boolean containsInvalidCharacters(String name) {
        // 한글, 영어, 숫자만 허용
        return !name.matches("^[a-zA-Z0-9가-힣]+$");
    }

}
