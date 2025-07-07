import java.util.Vector;

public class MazeFrame extends MyFrame {

	// 迷路の情報を格納する2次元配列
	private int[][] _mazeData;

	// 迷路の横
	private int _width = 25;
	// 迷路の縦
	private int _height = 25;

	// Cellを格納するVector配列
	private Vector<Cell> _cells;

	// 迷路の生成処理(未完成)
	private void mazeGenerate() {

		_mazeData = new int[_height][_width];

		/*// 外周壁、スタート、ゴールの設置処理
		for (int y = 0; y < _height; y++) {
			for (int x = 0; x < _width; x++) {

				int cellType = 0;
				// 外周を壁にする
				if (y == 0 || x == 0 || y == _height - 1 || x == _width - 1) {
					cellType = 1;
				}
				// スタート地点は左上
				else if (y == 1 && x == 1) {
					cellType = 2;
				}
				// ゴールは右下
				else if (y == _height - 2 && x == _width - 2) {
					cellType = 3;
				}
				_mazeData[y][x] = cellType;
			}
		}*/
		 // 初期化：全て壁にする
	    for (int y = 0; y < _height; y++) {
	        for (int x = 0; x < _width; x++) {
	            _mazeData[y][x] = 1;
	        }
	    }

	    // 掘り始めるスタート地点（奇数座標から）
	    dig(1, 1);

	    // スタート地点
	    _mazeData[1][1] = 2;

	    // ゴール地点（右下から逆に探して道になってるとこに置く）
	    for (int y = _height - 2; y >= 1; y--) {
	        for (int x = _width - 2; x >= 1; x--) {
	            if (_mazeData[y][x] == 0) {
	                _mazeData[y][x] = 3;
	                return;
	            }
	        }
	    }
	}
	
	private void dig(int x, int y) {
	    // 移動方向（上下左右）
	    int[][] directions = { {0, -2}, {2, 0}, {0, 2}, {-2, 0} };

	    // ランダムにシャッフル
	    java.util.Collections.shuffle(java.util.Arrays.asList(directions));

	    for (int[] dir : directions) {
	        int nx = x + dir[0];
	        int ny = y + dir[1];

	        if (nx > 0 && nx < _width - 1 && ny > 0 && ny < _height - 1 && _mazeData[ny][nx] == 1) {
	            // 間の壁を壊す
	            _mazeData[y + dir[1]/2][x + dir[0]/2] = 0;
	            _mazeData[ny][nx] = 0;
	            dig(nx, ny); // 再帰で掘り進める
	        }
	    }
	}

	public void run() {

		_cells = new Vector<Cell>();
		mazeGenerate();

		for (int y = 0; y < _height; y++) {
			for (int x = 0; x < _width; x++) {
				_cells.add(new Cell(x, y, _mazeData[y][x]));
			}
		}

		while (true) {
			clear();
			for (int i = 0; i < _cells.size(); i++) {
				_cells.get(i).draw(this);
			}

			sleep(0.1f);
		}
	}
}
