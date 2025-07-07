
public class Cell {

	// マスの大きさ
	int _squareSize = 20;

	// 位置
	int _x, _y;
	// マスの種類
	int _cellType;

	public Cell(int x, int y, int cellType) {
		_x = x;
		_y = y;
		_cellType = cellType;
	}

	public void draw(MyFrame frame) {

		switch (_cellType) {
		case 0:

			// 道
			// 何も描画しない
			
			break;
		case 1:

			// 壁
			frame.setColor(0, 0, 0);
			frame.fillRect(_x * _squareSize + 10, _y * _squareSize + 30 , _squareSize, _squareSize);

			break;
		case 2:

			// スタート地点
			frame.setColor(0, 0, 255);
			frame.fillRect(_x * _squareSize + 10, _y * _squareSize + 30, _squareSize, _squareSize);

			break;
		case 3:

			// ゴール
			frame.setColor(255, 0, 0);
			frame.fillRect(_x * _squareSize+10, _y * _squareSize+30, _squareSize, _squareSize);

			break;
		}

	}
}
