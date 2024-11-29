package componentsUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FrameResizer {
    private static final int BORDER_DRAG_THICKNESS = 8; // Thickness for resizing edges
    private final JFrame frame;

    public FrameResizer(JFrame frame) {
        this.frame = frame;
        attachListeners();
    }

    private void attachListeners() {
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (isOnBorder(e)) {
                    frame.setCursor(Cursor.getPredefinedCursor(getCursorType(e)));
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                frame.setCursor(Cursor.getDefaultCursor());
            }
        });

        frame.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                resizeFrame(e);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if (isOnBorder(e)) {
                    frame.setCursor(Cursor.getPredefinedCursor(getCursorType(e)));
                } else {
                    frame.setCursor(Cursor.getDefaultCursor());
                }
            }
        });
    }

    private boolean isOnBorder(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        int width = frame.getWidth();
        int height = frame.getHeight();

        return (x < BORDER_DRAG_THICKNESS || x > width - BORDER_DRAG_THICKNESS ||
                y < BORDER_DRAG_THICKNESS || y > height - BORDER_DRAG_THICKNESS);
    }

    private int getCursorType(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        int width = frame.getWidth();
        int height = frame.getHeight();

        if (x < BORDER_DRAG_THICKNESS && y < BORDER_DRAG_THICKNESS) {
            return Cursor.NW_RESIZE_CURSOR;
        } else if (x > width - BORDER_DRAG_THICKNESS && y < BORDER_DRAG_THICKNESS) {
            return Cursor.NE_RESIZE_CURSOR;
        } else if (x < BORDER_DRAG_THICKNESS && y > height - BORDER_DRAG_THICKNESS) {
            return Cursor.SW_RESIZE_CURSOR;
        } else if (x > width - BORDER_DRAG_THICKNESS && y > height - BORDER_DRAG_THICKNESS) {
            return Cursor.SE_RESIZE_CURSOR;
        } else if (x < BORDER_DRAG_THICKNESS) {
            return Cursor.W_RESIZE_CURSOR;
        } else if (x > width - BORDER_DRAG_THICKNESS) {
            return Cursor.E_RESIZE_CURSOR;
        } else if (y < BORDER_DRAG_THICKNESS) {
            return Cursor.N_RESIZE_CURSOR;
        } else if (y > height - BORDER_DRAG_THICKNESS) {
            return Cursor.S_RESIZE_CURSOR;
        } else {
            return Cursor.DEFAULT_CURSOR;
        }
    }

    private void resizeFrame(MouseEvent e) {
        Point mousePoint = e.getPoint();
        Dimension frameSize = frame.getSize();

        int x = mousePoint.x;
        int y = mousePoint.y;

        boolean isOnLeft = x < BORDER_DRAG_THICKNESS;
        boolean isOnRight = x > frameSize.width - BORDER_DRAG_THICKNESS;
        boolean isOnTop = y < BORDER_DRAG_THICKNESS;
        boolean isOnBottom = y > frameSize.height - BORDER_DRAG_THICKNESS;

        Rectangle newBounds = frame.getBounds();

        if (isOnLeft) {
            int dx = mousePoint.x;
            newBounds.x += dx;
            newBounds.width -= dx;
        }
        if (isOnRight) {
            newBounds.width = x;
        }
        if (isOnTop) {
            int dy = mousePoint.y;
            newBounds.y += dy;
            newBounds.height -= dy;
        }
        if (isOnBottom) {
            newBounds.height = y;
        }

        if (newBounds.width >= 100 && newBounds.height >= 100) { // Minimum frame size
            frame.setBounds(newBounds);
        }
    }
}
