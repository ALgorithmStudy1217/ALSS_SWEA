import java.util.*;

/*
원형 형태의 초밥 벨트와 L개의 의자가 존재
회전 초밥은 각 의자 앞에 여러 개 놓일 수 있음
초밥 벨트는 1초마다 한 칸씩 시계방향으로 회전
초기에 초밥 및 사람 존재 X
모든 초밥과 손님은 사라진다!!

명령어
1. 초밥 만들기
시각 t에 위치 x 앞에 name 이름을 부착한 회전 초밥 1개를 올려놓음.
단, 시각 t에 초밥 회전이 일어난 직후 발생
같은 위치에 여러 회전 초밥이 올라갈 수 있으며 자신의 이름이 적힌 초밥이 같은 위치에 여러 개 위치 가능
2. 손님 입장
name인 사람이 시각 t에 위치 x에 앉음.
단, 시각 t에 초밥 회전이 일어난 직후 발생
이때부터 위치 x 앞으로 오는 초밥들 중 자신의 이름이 적혀있는 초밥을 정확히 n개 먹고 떠남
시각 t에 위치 x에 자신의 이름이 적혀있는 초밥이 놓여 있다면 자리에 착석하는 즉시 먹음
자신의 이름이 적혀 있는 초밥이 같은 위치에 여러 개 있다면 동시에 여러 개 먹기 가능
주어지는 x 위치에는 사람이 없고 초밥을 먹는데 시간 소요 X
3. 사진 촬영
초밥 회전이 일어나고 손님이 자신의 이름이 적힌 초밥을 먹은 후,
사진 촬영 -> 남아있는 사람 수와 초밥 수 출력

조건
3≤L(초밥 벨트 길이)≤1,000,000,000
1≤Q(명령어 수)≤100,000
1≤t(시간 초)≤1,000,000,000
0 ≤ x(위치 index) ≤ L−1
1 ≤ name 의 길이 ≤ 30
1 ≤ 주어지는 서로 다른 name의 수 ≤ 15,000
입력으로 주어지는 t 값은 모두 다르며, 오름차순으로 정렬되어 주어짐

아이디어
초밥이 손님에게 도달하고 사라지는 시간을 계산하여 쿼리 생성
손님이 다 먹고 집을 떠나는 시간을 계산하여 쿼리 생성
가능한 이유 : 모든 초밥과 손님이 사라진다는 점과 주어진 모든 행동들이 시간 순으로 발생하기 때문

추가된 쿼리를 시간 순, 사진 촬영 쿼리를 마지막에 오도록 정렬 - O(NlogN)
순차적으로 쿼리 처리 - O(N)
*/

class Query {
	int cmd, t, x, n;
	String name;

	public Query(int cmd, int t, int x, int n, String name) {
		this.cmd = cmd;
		this.t = t;
		this.x = x;
		this.n = n;
		this.name = name;
	}
}

public class Main {
	static int L, Q;
	// 명령어 전체를 관리
	static List<Query> queries = new ArrayList<>();
	// 각 name에 대한 초밥 쿼리를 관리
	static Map<String, List<Query>> sushi_queries = new HashMap<>();
	// 초밥 집에 있는 사람 관리
	static Set<String> person = new HashSet<>();
	// 사람 위치 관리
	static Map<String, Integer> pos = new HashMap<>();
	// 사람 입장 시간
	static Map<String, Integer> entry_time = new HashMap<>();
	// 사람 퇴장 시간
	static Map<String, Integer> exit_time = new HashMap<>();

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		L = sc.nextInt();
		Q = sc.nextInt();
		// 1. 우선 모든 쿼리들을 입력받음
		while (Q-- > 0) {
			int cmd, t = -1, x = -1, n = -1;
			String name = "";
			cmd = sc.nextInt();
			// 초밥 만들기
			// 100 t x name 형태
			// 시각 t에 위치 x 앞에 name 이름을 부착한 회전 초밥을 올려 놓음
			if (cmd == 100) {
				t = sc.nextInt();
				x = sc.nextInt();
				name = sc.next();

				// Key 값이 존재하는 경우 Map의 Value의 값을 반환하고,
				// Key 값이 존재하지 않는 경우 Key와 Value를 Map에 저장하고 Null을 반환
//                sushi_queries.putIfAbsent(name, new ArrayList<>());

				// 처음 name에 해당하는 초밥이 올라오는 경우 get(name)은 null이기 때문에 체크!
				List<Query> tmp = sushi_queries.get(name);
				if (tmp == null)
					tmp = sushi_queries.put(name, new ArrayList<>());

				sushi_queries.get(name).add(new Query(cmd, t, x, n, name));
			}
			// 손님 입장
			// 200 t x name n 형태
			// 이름이 name인 사람이 시각 t에 위치 x에 가서 n개의 초밥을 먹은 후 떠남
			else if (cmd == 200) {
				t = sc.nextInt();
				x = sc.nextInt();
				name = sc.next();
				n = sc.nextInt();
				person.add(name);
				pos.put(name, x);
				entry_time.put(name, t);
			}
			// 사진 촬영
			// 300 t 형태
			// 남아있는 사람 수와 남아있는 초밥 수 출력
			else if (cmd == 300) {
				t = sc.nextInt();
			}
			queries.add(new Query(cmd, t, x, n, name));
		}

		// 손님이 초밥을 먹는 시간 및 퇴장 시간 처리
		// 최대 손님 15000명 손님을 Pi라 하고,
		// 해당 손님의 이름이 붙은 초밥을 모두 먹는 시간 Si라 할 때,
		// O(P1*S1 + P2*S2 + ...) -> O(P*S) -> 최악 O(Q) (Q는 쿼리 수(사진촬영 제외))
		for (String name : person) {
			for (Query cur_q : sushi_queries.get(name)) {
				int remove_t = 0;
				// 스시가 손님이 오기 전에 올라가 있는 경우
				if (entry_time.get(name) > cur_q.t) {
					// 현재 위치 (손님이 입장했을 때의 초밥 위치)
					// 1초마다 회전하므로 "손님이 입장한 시간 - 초밥이 올라간 시간"에서
					// 원형으로 순환되는 형태이므로 모듈러 연산 수행한 값과
					// 초밥이 올라간 위치를 더하면 손님이 입장했을 때의 초밥 위치 계산
					int cur_x = (cur_q.x + (entry_time.get(name) - cur_q.t)) % L;
					
					// 만나기까지 걸리는 시간 (몇 초후에 초밥을 만나는지)
					// "손님 위치 - 초밥 위치" 값이 음수인 경우를 방지하기 위해 벨트 길이만큼 덧셈 연산
					remove_t = entry_time.get(name) + (pos.get(name) + L - cur_x) % L;
				}
				// 손님이 온 후에 스시가 올라온 경우
				else {
					remove_t = cur_q.t + (pos.get(name) - cur_q.x + L) % L;
				}
				
				// 가장 처음 반복문이 돌아갔을 때, get(name) 값은 null이기 때문에 체크!
				Object cur_time = exit_time.get(name);
				if (cur_time == null) {
					cur_time = 0;
				}
				
				// 초밥이 사라지는 시간 중 가장 늦은 시간으로 시간을 수정
				exit_time.put(name, Math.max((int) cur_time, remove_t));
				
				// getOrDefault(Object key, V Defaultvalue)
				// 지정된 키로 매핑된 값이 없거나 null이면 defaultvalue 반환
//				exit_time.put(name,  Math.max(exit_time.getOrDefault(name, 0), remove_t));
				
				// 초밥이 사라지는 쿼리 추가
				queries.add(new Query(111, remove_t, -1, -1, name));
			}
			
			// 손님이 떠나는 쿼리 추가
			queries.add(new Query(222, exit_time.get(name), -1, -1, name));
		}

		// 쿼리 정렬
		// 시간 순으로 정렬하되, 사진 촬영이 가장 늦게 나오도록 정렬
		queries.sort((q1, q2) -> {
			if (q1.t != q2.t) {
				return Integer.compare(q1.t, q2.t);
			}
			return Integer.compare(q1.cmd, q2.cmd);
		});

		int sushi_cnt = 0;
		int person_cnt = 0;
		for (Query q : queries) {
			if (q.cmd == 100) {
				sushi_cnt++;
			} else if (q.cmd == 111) {
				sushi_cnt--;
			} else if (q.cmd == 200) {
				person_cnt++;
			} else if (q.cmd == 222) {
				person_cnt--;
			} else {
				System.out.println(person_cnt + " " + sushi_cnt);
			}
		}
	}
}
