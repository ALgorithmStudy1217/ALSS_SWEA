package Codetree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
/* 
NxM 격자 모든 위치에는 포탑 존재
각 포탑에는 공격력, 상황에 따라 줄거나 늘어날 수 있다
0 이하가 된다면 부서지며 더 이상 공격x

1턴은 4가지 액션을 순서대로 총 K턴
만약 부서지지 않은 포탑이 1개가 된다면 그 즉시 중지

1. 공격자 선정
부서지지 않은 포탑 중 가장 약한 포탑이 공격자로 선정
공격자로 선정되면 가장 약한 포탑이미ㅡ로 N+M만큼 공격력이 증가

    1. 공격력이 가장 낮은 포탑
    2. 2개 이상이라면, 가장 최근에 공격한 포탑
    3. 각 포탑 위치의 행과 열의 합이 가장 큰 포탑
    4. 열 값이 가장 큰 포탑

2. 공격자의 공격
자신을 제외한 가장 강한 포탑 공격
    1. 공격력이 가장 높은 포탑
    2. 공격한지 가장 오래된 포탑
    3. 행과 열의 합이 가장 작은 포탑
    4. 열 값이 가장 작은 포탑

    1) 레이저 공격 시도
        1. 상하좌우의 4개의 방향으로
        2. 부서진 포탑이 있는 위치는 지날 수 없다
        3. 가장자리에서 막힌 방향으로 진행하고자 한다면 반대편으로 나온다
    레이저 공격은 최단 경로로 공격
    똑같은 최단 경로가 2개 이상이라면, 우/하/좌/상의 우선순위대로 먼저 움직인 경로가 선택
    공격자의 공격력 만큼의 피해 수치만큼 공격력이 줄어든다
    레이저 경로에 있는 포탑도 절반 만큼의 공격을 받는다

    2) 존재하지 않으면 포탄 공격
    공격력 만큼의 피해
    추가적으로 주위 8개의 방향에 있는 포탑도 절반 만큼의 피해 가장자리면 반대편 격자에

3. 포탑 부서짐
공격력이 0 이하가 된 포탑은 부서진다.

4. 포탑 정비
부서지지 않은 포탑 중 공격과 무관했던 포탑은 공격력이 1씩 증가

전체 과정이 종료된 후 남아있는 포탑 중 가장 강한 포탑의 공격력을 출력하는 프로그램

N, M 4~10
K 1~1000
공격력 0~5000


*/
import java.util.Scanner;

public class CodeTree_포탑부수기 {
	static Scanner sc;

	static class Turret {
		int row;
		int col;
		int attack;
		int latestAttackTurn;
		boolean damaged;

		public Turret(int row, int col, int attack, int latestAttackTurn) {
			super();
			this.row = row;
			this.col = col;
			this.attack = attack;
			this.latestAttackTurn = latestAttackTurn;
			damaged = false;
		}

	}

	static int rowSize;
	static int colSize;
	static int turnCount;
	static int[][] map;
	static Turret[] turrets;

	static int remainTurrets;

	private static void play() {

		for (int turn = 1; turn <= turnCount; ++turn) {

			// 공격자 선정
			Turret attacker = chooseAttacker();
			attacker.latestAttackTurn = turn;
			// System.out.println(attacker.row + ", " + attacker.col + "공격");
			// 공격
			Turret target = chooseTarget();
			attacker.attack += rowSize + colSize;
			// System.out.println(target.row + ", " + target.col + "맞음");
			List<int[]> attackList = aimLazer(attacker, target);
			if (attackList == null) {
				// 포탄 공격
				boomb(attacker, target);
			} else {
				// 레이저 공격
				lazer(attackList, attacker, target);
			}

			// 포탑 부서짐
			update(attacker);

			if (remainTurrets == 1) {
				break;
			}
			// 포탑 정비
			repair();
			
//			for (int row = 0 ; row < rowSize; ++row) {
//				for (int col = 0; col < colSize; ++col) {
//					System.out.print(map[row][col]+" ");
//				}
//				System.out.println();
//			}
		}
		
		// 남아있는 포탑중 가장 강한 포탑의 공격력
		int maxAttack = findMaxAttack();
		System.out.println(maxAttack);
	}

	private static int findMaxAttack() {
		int maxAttack = 0;
		
		for (Turret turret : turrets) {
			if (turret.attack > maxAttack) {
				maxAttack = turret.attack;
			}
		}
		
		return maxAttack;
	}

	private static void repair() {

		for (Turret turret : turrets ) {
			if (turret.attack <= 0) {
				continue;
			}
			if (turret.damaged) {
				turret.damaged = false;
			} else {
				turret.attack++;
				map[turret.row][turret.col]++; 
			}
		}
	}

	private static void update(Turret attacker) {

		int attack = attacker.attack;
		for (Turret turret : turrets) {
			if (map[turret.row][turret.col] <= 0) {
				if (turret.attack > 0) {
					turret.attack = 0;
					remainTurrets--;
				}
				map[turret.row][turret.col] = 0;
			} else {
				if (map[turret.row][turret.col] < turret.attack) {
					turret.attack = map[turret.row][turret.col];
					turret.damaged = true;
				}
			}
		}
		map[attacker.row][attacker.col] = attack;
		attacker.attack = attack;
	}

	private static void lazer(List<int[]> attackList, Turret attacker, Turret target) {
		for (int[] turret : attackList) {
			if (turret[0] == attacker.row && turret[1] == attacker.col) {
				continue;
			}
			if (turret[0] == target.row && turret[1] == target.col) {
				map[turret[0]][turret[1]] -= attacker.attack;
				continue;
			}
			map[turret[0]][turret[1]] -= attacker.attack / 2;
		}
	}

	private static final int[] ROW_BOOM = { -1, -1, -1, 0, 0, 1, 1, 1 };
	private static final int[] COL_BOOM = { -1, 0, 1, -1, 1, -1, 0, 1 };

	private static void boomb(Turret attacker, Turret target) {
		map[target.row][target.col] -= attacker.attack;

		for (int boom = 0; boom < ROW_BOOM.length; ++boom) {
			int row = target.row + ROW_BOOM[boom];
			int col = target.col + COL_BOOM[boom];

			if (row < 0) {
				row = rowSize - 1;
			} else if (row >= rowSize) {
				row = 0;
			}
			if (col < 0) {
				col = colSize - 1;
			} else if (col >= colSize) {
				col = 0;
			}

			if (row == attacker.row && col == attacker.col) {
				continue;
			}
			if (map[row][col] == 0) {
				continue;
			}

			map[row][col] -= attacker.attack / 2;
		}
	}

	private static final int[] ROW_DELTA = { 0, 1, 0, -1 };
	private static final int[] COL_DELTA = { 1, 0, -1, 0 };

	static class Path {
		int row;
		int col;
		List<int[]> path;

		public Path(int row, int col, List<int[]> path) {
			this.row = row;
			this.col = col;
			this.path = new ArrayList<>(path);
			this.path.add(new int[] { row, col });
		}
	}

	private static List<int[]> aimLazer(Turret attacker, Turret target) {

		Queue<Path> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[rowSize][colSize];

		queue.add(new Path(attacker.row, attacker.col, new ArrayList<>()));
		visited[attacker.row][attacker.col] = true;

		while (!queue.isEmpty()) {

			Path cur = queue.poll();
			if (cur.row == target.row && cur.col == target.col) {
				return cur.path;
			}

			for (int dir = 0; dir < ROW_DELTA.length; ++dir) {
				int row = cur.row + ROW_DELTA[dir];
				int col = cur.col + COL_DELTA[dir];

				if (row < 0) {
					row = rowSize - 1;
				} else if (row >= rowSize) {
					row = 0;
				}
				if (col < 0) {
					col = colSize - 1;
				} else if (col >= colSize) {
					col = 0;
				}

				if (visited[row][col]) {
					continue;
				}
				if (map[row][col] == 0) {
					continue;
				}
				visited[row][col] = true;
				queue.add(new Path(row, col, cur.path));
			}

		}

		return null;
	}

	private static Turret chooseTarget() {

		Turret target = null;

		for (Turret turret : turrets) {
			if (turret.attack <= 0) {
				continue;
			}
			if (target == null) {
				target = turret;
				continue;
			}
			// 1. 공격력이 가장 높은 포탑
			if (turret.attack > target.attack) {
				target = turret;	
			} else if (turret.attack == target.attack) {
				// 공격한지 가장 오래된 포탑
				if (turret.latestAttackTurn < target.latestAttackTurn) {
					target = turret;
				} else if (turret.latestAttackTurn == target.latestAttackTurn) {
					// 행과 열의 합이 가장 작은 포탑
					int curPos = turret.row + turret.col;
					int targetPos = target.row + target.col;
					if (curPos < targetPos) {
						target = turret;
					} else if (curPos == targetPos) {
						// 열 값이 가장 작은 포탑
						if (turret.col < target.col) {
							target = turret;
						}
					}

				}
			}
		}

		return target;
	}

	private static Turret chooseAttacker() {

		Turret turret = null;
		for (Turret curTurret : turrets) {
			if (curTurret.attack <= 0) {
				continue;
			}
			if (turret == null) {
				turret = curTurret;
				continue;
			}
			// 1. 공격력이 가장 낮은 포탑
			if (curTurret.attack < turret.attack) {
				turret = curTurret;
				continue;
			} else if (curTurret.attack == turret.attack) {
				// 2개 이상이라면 가장 최근에 공격한 포탑
				if (curTurret.latestAttackTurn > turret.latestAttackTurn) {
					turret = curTurret;
					continue;
				} else if (curTurret.latestAttackTurn == turret.latestAttackTurn) {
					// 행과 열의 합이 가장 큰 포탑
					int curPos = curTurret.row + curTurret.col;
					int pos = turret.row + turret.col;
					if (curPos > pos) {
						turret = curTurret;
						continue;
					} else if (curPos == pos) {
						// 열 값이 가장 큰 포탑
						if (curTurret.col > turret.col) {
							turret = curTurret;
							continue;
						}
					}
				}
			}
		}

		return turret;
	}

	private static void init() {
		rowSize = sc.nextInt();
		colSize = sc.nextInt();
		turnCount = sc.nextInt();

		remainTurrets = 0;
		map = new int[rowSize][colSize];
		turrets = new Turret[rowSize * colSize];
		int turretIndex = 0;
		for (int row = 0; row < rowSize; ++row) {
			for (int col = 0; col < colSize; ++col) {
				map[row][col] = sc.nextInt();
				turrets[turretIndex++] = new Turret(row, col, map[row][col], 0);
				if (map[row][col] > 0) {
					remainTurrets++;
				}
			}
		}
	}

	public static void main(String[] args) {
		sc = new Scanner(System.in);

		init();
		play();
	}
}