package tetris.blocksandmoves;

import tetris.controls.Controller;
import javafx.scene.shape.Rectangle;

/**
 * Luokasta löytyy blockkien osien kääntämiseen (nuolinäppäin UP) tarvittavat
 * metodit. Metodit ovat blokkikohtaisia.
 */
public class Turns {

    int sqSize;
    int move;
    int boardWidth;
    int boardHeight;
    int[][] board = Controller.board;

    public Turns(int sqS, int move, int boardWidth, int boardHeight) {
        this.sqSize = sqS;
        this.move = move;
        this.boardWidth = boardWidth;
        this.boardHeight = boardHeight;
    }

    /**
     * Metodi ohjaa line-nimisen blockin kääntämistaä osa kerrallaan. 
     * If-lauseissa kutsutaan metodeita tarkastamaan, missä asennossa palikka
     * siirtohetkellä on, ja mahtuuko kääntämään. Jos mahtuu, metorissa kutsutaan 
     * kunkin siirron metodia kääntämään blockin jokainen osa. 
     *
     * @param block blokki, jota ollaan kääntämässä.
     */
    public void turnLine(Block block) {
        Rectangle a = block.a;
        Rectangle b = block.b;
        Rectangle c = block.c;
        Rectangle d = block.d;

        if (lineTurnIf1(a, b, c, d)) {
            turnLineFromPos1(a, b, c, d);

        } else if (lineTurnIf2(a, b, c, d)) {
            turnLineFromPos2(a, b, c, d);

        } else if (lineTurnIf3(a, b, c, d)) {
            turnLineFromPos3(a, b, c, d);

        } else if (lineTurnIf4(a, b, c, d)) {
            turnLineFromPos4(a, b, c, d);
        }
    }
    /**
     * Katsoo onko line-ensimmäisessä asennossa ja onko sillä tilaa kääntyä
     * toiseen asentoon. Parametreinä blockin osat.
     * 
     * @return true jos on asennossa 1 ja on tilaa kääntyä.
     */
    public boolean lineTurnIf1(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
        boolean isIf = false;

        if (a.getX() < b.getX() && (int) a.getY() >= 62) {
            if (isTurnOk((int) a.getX() + 2 * sqSize, (int) a.getY() - 2 * sqSize)
                    && isTurnOk((int) b.getX() + 1 * sqSize, (int) b.getY() - 1 * sqSize)
                    && isTurnOk((int) d.getX() - 1 * sqSize, (int) d.getY() + 1 * sqSize)) {
                isIf = true;
            }
        }

        return isIf;
    }

    public void turnLineFromPos1(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
        partRight(a);
        partRight(a);
        partUp(a);
        partUp(a);
        partUp(b);
        partRight(b);
        partLeft(d);
        partDown(d);
    }
    /**
     * Katsoo onko line-toisessa asennossa ja onko sillä tilaa kääntyä
     * kolmanteen asentoon. Parametreinä blockin osat.
     * 
     * @return true jos on asennossa 2 ja on tilaa kääntyä.
     */
    public boolean lineTurnIf2(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
        boolean isIf = false;

        if (a.getY() < b.getY() && (int) a.getX() + 62 < boardWidth && (int) d.getX() >= 31) {
            if (isTurnOk((int) a.getX() + 2 * sqSize, (int) a.getY() + 2 * sqSize)
                    && isTurnOk((int) b.getX() + 1 * sqSize, (int) b.getY() + 1 * sqSize)
                    && isTurnOk((int) d.getX() - 1 * sqSize, (int) d.getY() - 1 * sqSize)) {
                isIf = true;
            }
        }

        return isIf;
    }

    public void turnLineFromPos2(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
        partRight(a);
        partRight(a);
        partDown(a);
        partDown(a);
        partRight(b);
        partDown(b);
        partLeft(d);
        partUp(d);
    }
    /**
     * Katsoo onko line-kolmannessa asennossa ja onko sillä tilaa kääntyä
     * neljänteen asentoon. Parametreinä blockin osat.
     * 
     * @return true jos on asennossa 3 ja on tilaa kääntyä.
     */
    public boolean lineTurnIf3(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
        boolean isIf = false;

        if (a.getX() > b.getX() && isTurnOk((int) a.getX() - 1 * sqSize, (int) a.getY() + 1 * sqSize)
                && isTurnOk((int) c.getX() + 1 * sqSize, (int) c.getY() - 1 * sqSize)
                && isTurnOk((int) d.getX() + 2 * sqSize, (int) d.getY() - 2 * sqSize)) {
            isIf = true;
        }

        return isIf;
    }

    public void turnLineFromPos3(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
        partLeft(a);
        partDown(a);
        partRight(c);
        partUp(c);
        partRight(d);
        partRight(d);
        partUp(d);
        partUp(d);
    }
    
    /**
     * Katsoo onko line-neljännessä asennossa ja onko sillä tilaa kääntyä
     * viidenteen asentoon. Parametreinä blockin osat.
     * 
     * @return true jos on asennossa 4 ja on tilaa kääntyä.
     */
    public boolean lineTurnIf4(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
        boolean isIf = false;

        if (a.getY() > b.getY() && (int) d.getX() + 62 < boardWidth && (int) a.getX() >= 31) {
            if (isTurnOk((int) a.getX() - 1 * sqSize, (int) a.getY() - 1 * sqSize)
                    && isTurnOk((int) c.getX() + 1 * sqSize, (int) c.getY() + 1 * sqSize)
                    && isTurnOk((int) d.getX() + 2 * sqSize, (int) d.getY() + 2 * sqSize)) {
                isIf = true;
            }
        }

        return isIf;
    }

    public void turnLineFromPos4(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
        partLeft(a);
        partUp(a);
        partRight(c);
        partDown(c);
        partRight(d);
        partRight(d);
        partDown(d);
        partDown(d);
    }

    /**
     * Metodi ohjaa Ess-nimisen blockin kääntämistaä osa kerrallaan. 
     * If-lauseissa kutsutaan metodeita tarkastamaan, missä asennossa palikka
     * siirtohetkellä on, ja mahtuuko kääntämään. Jos mahtuu, metorissa kutsutaan 
     * kunkin siirron metodia kääntämään blockin jokainen osa. 
     *
     * @param block blokki, jota ollaan kääntämässä.
     */
    public void turnEss(Block block) {
        Rectangle a = block.a;
        Rectangle b = block.b;
        Rectangle c = block.c;
        Rectangle d = block.d;

        if (essTurnIf1(a, b, c, d)) {
            turnEssFromPos1(a, b, c, d);

        } else if (essTurnIf2(a, b, c, d)) {
            turnEssFromPos2(a, b, c, d);

        } else if (essTurnIf3(a, b, c, d)) {
            turnEssFromPos3(a, b, c, d);

        } else if (essTurnIf4(a, b, c, d)) {
            turnEssFromPos4(a, b, c, d);
        }
    }
    /**
     * Katsoo onko ess-ensimmäisessä asennossa ja onko sillä tilaa kääntyä
     * toiseen asentoon. Parametreinä blockin osat.
     * 
     * @return true jos on asennossa 1 ja on tilaa kääntyä.
     */
    public boolean essTurnIf1(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
        boolean isIf = false;

        if (b.getX() < c.getX() && (int) a.getX() < boardWidth - 62) {
            if (isTurnOk((int) a.getX() + 2 * sqSize, (int) a.getY())
                    && isTurnOk((int) b.getX() + 1 * sqSize, (int) b.getY() - 1 * sqSize)
                    && isTurnOk((int) d.getX() - 1 * sqSize, (int) d.getY() - 1 * sqSize)) {
                isIf = true;
            }
        }

        return isIf;
    }

    public void turnEssFromPos1(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
        partRight(a);
        partRight(a);
        partRight(b);
        partUp(b);
        partLeft(d);
        partUp(d);
    }
    /**
     * Katsoo onko ess-toisessa asennossa ja onko sillä tilaa kääntyä
     * kolmanteen asentoon. Parametreinä blockin osat.
     * 
     * @return true jos on asennossa 2 ja on tilaa kääntyä.
     */
    public boolean essTurnIf2(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
        boolean isIf = false;

        if (b.getY() < c.getY() && isTurnOk((int) a.getX(), (int) a.getY() + 2 * sqSize)
                && isTurnOk((int) b.getX() + 1 * sqSize, (int) b.getY() + 1 * sqSize)
                && isTurnOk((int) d.getX() + 1 * sqSize, (int) d.getY() - 1 * sqSize)) {
            isIf = true;
        }

        return isIf;
    }

    public void turnEssFromPos2(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
        partDown(a);
        partDown(a);
        partRight(b);
        partDown(b);
        partRight(d);
        partUp(d);
    }

    /**
     * Katsoo onko ess-kolmannessa asennossa ja onko sillä tilaa kääntyä
     * neljänteen asentoon. Parametreinä blockin osat.
     * 
     * @return true jos on asennossa 3 ja on tilaa kääntyä.
     */    public boolean essTurnIf3(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
        boolean isIf = false;

        if (b.getX() > c.getX() && isTurnOk((int) a.getX() - 2 * sqSize, (int) a.getY())
                && isTurnOk((int) b.getX() - 1 * sqSize, (int) b.getY() + 1 * sqSize)
                && isTurnOk((int) d.getX() + 1 * sqSize, (int) d.getY() + 1 * sqSize)) {
            isIf = true;
        }

        return isIf;
    }

    public void turnEssFromPos3(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
        partLeft(a);
        partLeft(a);
        partLeft(b);
        partDown(b);
        partRight(d);
        partDown(d);
    }
    /**
     * Katsoo onko ess-neljännessä asennossa ja onko sillä tilaa kääntyä
     * viidenteen asentoon. Parametreinä blockin osat.
     * 
     * @return true jos on asennossa 4 ja on tilaa kääntyä.
     */
    public boolean essTurnIf4(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
        boolean isIf = false;

        if (b.getY() > c.getY() && isTurnOk((int) a.getX(), (int) a.getY() - 2 * sqSize)
                && isTurnOk((int) b.getX() - 1 * sqSize, (int) b.getY() - 1 * sqSize)
                && isTurnOk((int) d.getX() - 1 * sqSize, (int) d.getY() + 1 * sqSize)) {
            isIf = true;
        }

        return isIf;
    }

    public void turnEssFromPos4(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
        partUp(a);
        partUp(a);
        partLeft(b);
        partUp(b);
        partLeft(d);
        partDown(d);
    }

    /**
     * Metodi ohjaa Enn-nimisen blockin kääntämistaä osa kerrallaan. 
     * If-lauseissa kutsutaan metodeita tarkastamaan, missä asennossa palikka
     * siirtohetkellä on, ja mahtuuko kääntämään. Jos mahtuu, metorissa kutsutaan 
     * kunkin siirron metodia kääntämään blockin jokainen osa. 
     *
     * @param block blokki, jota ollaan kääntämässä.
     */
    public void turnEnn(Block block) {
        Rectangle a = block.a;
        Rectangle b = block.b;
        Rectangle c = block.c;
        Rectangle d = block.d;

        if (ennTurnIf1(a, b, c, d)) {
            turnEnnFromPos1(a, b, c, d);

        } else if (ennTurnIf2(a, b, c, d)) {
            turnEnnFromPos2(a, b, c, d);

        } else if (ennTurnIf3(a, b, c, d)) {
            turnEnnFromPos3(a, b, c, d);

        } else if (ennTurnIf4(a, b, c, d)) {
            turnEnnFromPos4(a, b, c, d);
        }
    }
    /**
     * Katsoo onko enn-ensimmäisessä asennossa ja onko sillä tilaa kääntyä
     * toiseen asentoon. Parametreinä blockin osat.
     * 
     * @return true jos on asennossa 1 ja on tilaa kääntyä.
     */
    public boolean ennTurnIf1(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
        boolean isIf = false;

        if (b.getX() < c.getX() && (int) a.getX() < boardWidth - 62) {
            if (isTurnOk((int) a.getX() + 1 * sqSize, (int) a.getY() + 1 * sqSize)
                    && isTurnOk((int) b.getX() + 1 * sqSize, (int) b.getY() - 1 * sqSize)
                    && isTurnOk((int) d.getX(), (int) d.getY() - 2 * sqSize)) {
                isIf = true;
            }
        }

        return isIf;
    }

    public void turnEnnFromPos1(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
        partRight(a);
        partDown(a);
        partRight(b);
        partUp(b);
        partUp(d);
        partUp(d);
    }
    /**
     * Katsoo onko enn-toisessa asennossa ja onko sillä tilaa kääntyä
     * kolmanteen asentoon. Parametreinä blockin osat.
     * 
     * @return true jos on asennossa 2 ja on tilaa kääntyä.
     */
    public boolean ennTurnIf2(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
        boolean isIf = false;

        if (b.getY() < c.getY() && isTurnOk((int) a.getX() - 1 * sqSize, (int) a.getY() + 1 * sqSize)
                && isTurnOk((int) b.getX() + 1 * sqSize, (int) b.getY() + 1 * sqSize)
                && isTurnOk((int) d.getX() + 2 * sqSize, (int) d.getY())) {
            isIf = true;
        }

        return isIf;
    }

    public void turnEnnFromPos2(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
        partLeft(a);
        partDown(a);
        partRight(b);
        partDown(b);
        partRight(d);
        partRight(d);
    }
    /**
     * Katsoo onko enn-kolmannessa asennossa ja onko sillä tilaa kääntyä
     * neljänteen asentoon. Parametreinä blockin osat.
     * 
     * @return true jos on asennossa 3 ja on tilaa kääntyä.
     */
    public boolean ennTurnIf3(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
        boolean isIf = false;

        if (b.getX() > c.getX() && (int) a.getX() >= 32) {
            if (isTurnOk((int) a.getX() - 1 * sqSize, (int) a.getY() - 1 * sqSize)
                    && isTurnOk((int) b.getX() - 1 * sqSize, (int) b.getY() + 1 * sqSize)
                    && isTurnOk((int) d.getX(), (int) d.getY() + 2 * sqSize)) {
                isIf = true;
            }
        }

        return isIf;
    }

    public void turnEnnFromPos3(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
        partLeft(a);
        partUp(a);
        partLeft(b);
        partDown(b);
        partDown(d);
        partDown(d);
    }
    /**
     * Katsoo onko enn-neljännessä asennossa ja onko sillä tilaa kääntyä
     * viidenteen asentoon. Parametreinä blockin osat.
     * 
     * @return true jos on asennossa 4 ja on tilaa kääntyä.
     */
    public boolean ennTurnIf4(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
        boolean isIf = false;

        if (b.getY() > c.getY() && isTurnOk((int) a.getX() + 1 * sqSize, (int) a.getY() - 1 * sqSize)
                && isTurnOk((int) b.getX() - 1 * sqSize, (int) b.getY() - 1 * sqSize)
                && isTurnOk((int) d.getX() - 2 * sqSize, (int) d.getY())) {
            isIf = true;
        }

        return isIf;
    }

    public void turnEnnFromPos4(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
        partRight(a);
        partUp(a);
        partLeft(b);
        partUp(b);
        partLeft(d);
        partLeft(d);
    }

    /**
     * Metodi ohjaa dude-nimisen blockin kääntämistaä osa kerrallaan. 
     * If-lauseissa kutsutaan metodeita tarkastamaan, missä asennossa palikka
     * siirtohetkellä on, ja mahtuuko kääntämään. Jos mahtuu, metorissa kutsutaan 
     * kunkin siirron metodia kääntämään blockin jokainen osa. 
     *
     * @param block blokki, jota ollaan kääntämässä.
     */
    public void turnDude(Block block) {
        Rectangle a = block.a;
        Rectangle b = block.b;
        Rectangle c = block.c;
        Rectangle d = block.d;

        if (dudeTurnIf1(a, b, c, d)) {
            turnDudeFromPos1(a, b, c, d);

        } else if (dudeTurnIf2(a, b, c, d)) {
            turnDudeFromPos2(a, b, c, d);

        } else if (dudeTurnIf3(a, b, c, d)) {
            turnDudeFromPos3(a, b, c, d);

        } else if (dudeTurnIf4(a, b, c, d)) {
            turnDudeFromPos4(a, b, c, d);
        }
    }
    /**
     * Katsoo onko dude-ensimmäisessä asennossa ja onko sillä tilaa kääntyä
     * toiseen asentoon. Parametreinä blockin osat.
     * 
     * @return true jos on asennossa 1 ja on tilaa kääntyä.
     */
    public boolean dudeTurnIf1(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
        boolean isIf = false;

        if (b.getX() < c.getX() && (int) a.getX() < boardWidth - 62 && (int) d.getX() > 31) {
            if (isTurnOk((int) a.getX() + 2 * sqSize, (int) a.getY())
                    && isTurnOk((int) b.getX() + 1 * sqSize, (int) b.getY() - 1 * sqSize)
                    && isTurnOk((int) d.getX(), (int) d.getY() - 2 * sqSize)) {
                isIf = true;
            }
        }

        return isIf;
    }

    public void turnDudeFromPos1(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
        partRight(a);
        partRight(a);
        partRight(b);
        partUp(b);
        partUp(d);
        partUp(d);
    }
    /**
     * Katsoo onko dude-toisessa asennossa ja onko sillä tilaa kääntyä
     * kolmanteen asentoon. Parametreinä blockin osat.
     * 
     * @return true jos on asennossa 2 ja on tilaa kääntyä.
     */
    public boolean dudeTurnIf2(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
        boolean isIf = false;

        if (b.getY() < c.getY() && isTurnOk((int) a.getX(), (int) a.getY() + 2 * sqSize)
                && isTurnOk((int) b.getX() + 1 * sqSize, (int) b.getY() + 1 * sqSize)
                && isTurnOk((int) d.getX() + 2 * sqSize, (int) d.getY())) {
            isIf = true;
        }

        return isIf;
    }

    public void turnDudeFromPos2(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
        partDown(a);
        partDown(a);
        partRight(b);
        partDown(b);
        partRight(d);
        partRight(d);
    }
    /**
     * Katsoo onko dude kolmannessa asennossa ja onko sillä tilaa kääntyä
     * neljänteen asentoon. Parametreinä blockin osat.
     * 
     * @return true jos on asennossa 3 ja on tilaa kääntyä.
     */
    public boolean dudeTurnIf3(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
        boolean isIf = false;

        if (b.getX() > c.getX() && isTurnOk((int) a.getX() - 2 * sqSize, (int) a.getY())
                && isTurnOk((int) b.getX() - 1 * sqSize, (int) b.getY() + 1 * sqSize)
                && isTurnOk((int) d.getX(), (int) d.getY() + 2 * sqSize)) {
            isIf = true;
        }

        return isIf;
    }

    public void turnDudeFromPos3(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
        partLeft(a);
        partLeft(a);
        partLeft(b);
        partDown(b);
        partDown(d);
        partDown(d);
    }
    /**
     * Katsoo onko dude-neljännessä asennossa ja onko sillä tilaa kääntyä
     * viidenteen asentoon. Parametreinä blockin osat.
     * 
     * @return true jos on asennossa 4 ja on tilaa kääntyä.
     */
    public boolean dudeTurnIf4(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
        boolean isIf = false;

        if (b.getY() > c.getY() && isTurnOk((int) a.getX(), (int) a.getY() - 2 * sqSize)
                && isTurnOk((int) b.getX() - 1 * sqSize, (int) b.getY() - 1 * sqSize)
                && isTurnOk((int) d.getX() - 2 * sqSize, (int) d.getY())) {
            isIf = true;
        }

        return isIf;
    }

    public void turnDudeFromPos4(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
        partUp(a);
        partUp(a);
        partLeft(b);
        partUp(b);
        partLeft(d);
        partLeft(d);
    }

    /**
     * Metodi ohjaa Jei-nimisen blockin kääntämistaä osa kerrallaan. 
     * If-lauseissa kutsutaan metodeita tarkastamaan, missä asennossa palikka
     * siirtohetkellä on, ja mahtuuko kääntämään. Jos mahtuu, metorissa kutsutaan 
     * kunkin siirron metodia kääntämään blockin jokainen osa. 
     *
     * @param block blokki, jota ollaan kääntämässä.
     */
    public void turnJei(Block block) {
        Rectangle a = block.a;
        Rectangle b = block.b;
        Rectangle c = block.c;
        Rectangle d = block.d;

        if (jeiTurnIf1(a, b, c, d)) {
            turnJeiFromPos1(a, b, c, d);

        } else if (jeiTurnIf2(a, b, c, d)) {
            turnJeiFromPos2(a, b, c, d);

        } else if (jeiTurnIf3(a, b, c, d)) {
            turnJeiFromPos3(a, b, c, d);

        } else if (jeiTurnIf4(a, b, c, d)) {
            turnJeiFromPos4(a, b, c, d);
        }
    }

    /**
     * Katsoo onko jei-ensimmäisessä asennossa ja onko sillä tilaa kääntyä
     * toiseen asentoon. Parametreinä blockin osat.
     * 
     * @return true jos on asennossa 1 ja on tilaa kääntyä.
     */
    public boolean jeiTurnIf1(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
        boolean isIf = false;

        if (b.getY() < d.getY() && (int) a.getX() < boardWidth - 31) {
            if (isTurnOk((int) a.getX() + 1 * sqSize, (int) a.getY() + 1 * sqSize)
                    && isTurnOk((int) c.getX(), (int) c.getY() - 2 * sqSize)
                    && isTurnOk((int) d.getX() - 1 * sqSize, (int) d.getY() - 1 * sqSize)) {
                isIf = true;
            }
        }

        return isIf;
    }

    public void turnJeiFromPos1(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
        partRight(a);
        partDown(a);
        partUp(c);
        partUp(c);
        partLeft(d);
        partUp(d);
    }
    /**
     * Katsoo onko jei-toisessa asennossa ja onko sillä tilaa kääntyä
     * kolmanteen asentoon. Parametreinä blockin osat.
     * 
     * @return true jos on asennossa 2 ja on tilaa kääntyä.
     */
    public boolean jeiTurnIf2(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
        boolean isIf = false;

        if (b.getX() > d.getX() && isTurnOk((int) a.getX() - 1 * sqSize, (int) a.getY() + 1 * sqSize)
                && isTurnOk((int) c.getX() + 2 * sqSize, (int) c.getY())
                && isTurnOk((int) d.getX() + 1 * sqSize, (int) d.getY() - 1 * sqSize)) {
            isIf = true;
        }

        return isIf;
    }

    public void turnJeiFromPos2(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
        partLeft(a);
        partDown(a);
        partRight(c);
        partRight(c);
        partRight(d);
        partUp(d);
    }
    /**
     * Katsoo onko jei-kolmannessa asennossa ja onko sillä tilaa kääntyä
     * neljänteen asentoon. Parametreinä blockin osat.
     * 
     * @return true jos on asennossa 3 ja on tilaa kääntyä.
     */
    public boolean jeiTurnIf3(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
        boolean isIf = false;

        if (b.getY() > d.getY() && (int) a.getX() > 31) {
            if (isTurnOk((int) a.getX() - 1 * sqSize, (int) a.getY() - 1 * sqSize)
                    && isTurnOk((int) c.getX(), (int) c.getY() + 2 * sqSize)
                    && isTurnOk((int) d.getX() + 1 * sqSize, (int) d.getY() + 1 * sqSize)) {
                isIf = true;
            }
        }

        return isIf;
    }

    public void turnJeiFromPos3(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
        partLeft(a);
        partUp(a);
        partDown(c);
        partDown(c);
        partRight(d);
        partDown(d);
    }
    /**
     * Katsoo onko jei-neljännessä asennossa ja onko sillä tilaa kääntyä
     * viidenteen asentoon. Parametreinä blockin osat.
     * 
     * @return true jos on asennossa 4 ja on tilaa kääntyä.
     */
    public boolean jeiTurnIf4(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
        boolean isIf = false;

        if (b.getX() < d.getX() && isTurnOk((int) a.getX() + 1 * sqSize, (int) a.getY() - 1 * sqSize)
                && isTurnOk((int) c.getX() - 2 * sqSize, (int) c.getY())
                && isTurnOk((int) d.getX() - 1 * sqSize, (int) d.getY() + 1 * sqSize)) {
            isIf = true;
        }

        return isIf;
    }

    public void turnJeiFromPos4(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
        partRight(a);
        partUp(a);
        partLeft(c);
        partLeft(c);
        partLeft(d);
        partDown(d);
    }

    /**
     * Metodi ohjaa ell-nimisen blockin kääntämistaä osa kerrallaan. 
     * If-lauseissa kutsutaan metodeita tarkastamaan, missä asennossa palikka
     * siirtohetkellä on, ja mahtuuko kääntämään. Jos mahtuu, metorissa kutsutaan 
     * kunkin siirron metodia kääntämään blockin jokainen osa. 
     *
     * @param block blokki, jota ollaan kääntämässä.
     */
    public void turnEll(Block block) {
        Rectangle a = block.a;
        Rectangle b = block.b;
        Rectangle c = block.c;
        Rectangle d = block.d;

        if (ellTurnIf1(a, b, c, d)) {
            turnEllFromPos1(a, b, c, d);

        } else if (ellTurnIf2(a, b, c, d)) {
            turnEllFromPos2(a, b, c, d);

        } else if (ellTurnIf3(a, b, c, d)) {
            turnEllFromPos3(a, b, c, d);

        } else if (ellTurnIf4(a, b, c, d)) {
            turnEllFromPos4(a, b, c, d);
        }
    }
    /**
     * Katsoo onko lell-ensimmäisessä asennossa ja onko sillä tilaa kääntyä
     * toiseen asentoon. Parametreinä blockin osat.
     * 
     * @return true jos on asennossa 1 ja on tilaa kääntyä.
     */
    public boolean ellTurnIf1(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
        boolean isIf = false;

        if (b.getY() < c.getY() && (int) c.getX() > 31) {
            if (isTurnOk((int) a.getX() + 1 * sqSize, (int) a.getY() + 1 * sqSize)
                    && isTurnOk((int) c.getX() - 1 * sqSize, (int) c.getY() - 1 * sqSize)
                    && isTurnOk((int) d.getX() - 2 * sqSize, (int) d.getY())) {
                isIf = true;
            }
        }

        return isIf;
    }

    public void turnEllFromPos1(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
        partRight(a);
        partDown(a);
        partLeft(c);
        partUp(c);
        partLeft(d);
        partLeft(d);
    }
    /**
     * Katsoo onko ell-toisessa asennossa ja onko sillä tilaa kääntyä
     * kolmanteen asentoon. Parametreinä blockin osat.
     * 
     * @return true jos on asennossa 2 ja on tilaa kääntyä.
     */
    public boolean ellTurnIf2(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
        boolean isIf = false;

        if (b.getX() > c.getX() && isTurnOk((int) a.getX() - 1 * sqSize, (int) a.getY() + 1 * sqSize)
                && isTurnOk((int) c.getX() + 1 * sqSize, (int) c.getY() - 1 * sqSize)
                && isTurnOk((int) d.getX(), (int) d.getY() - 2 * sqSize)) {
            isIf = true;
        }

        return isIf;
    }

    public void turnEllFromPos2(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
        partLeft(a);
        partDown(a);
        partRight(c);
        partUp(c);
        partUp(d);
        partUp(d);
    }
    /**
     * Katsoo onko ell-kolmannessa asennossa ja onko sillä tilaa kääntyä
     * neljänteen asentoon. Parametreinä blockin osat.
     * 
     * @return true jos on asennossa 3 ja on tilaa kääntyä.
     */
    public boolean ellTurnIf3(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
        boolean isIf = false;

        if (b.getY() > c.getY() && (int) c.getX() < boardWidth - 32) {
            if (isTurnOk((int) a.getX() - 1 * sqSize, (int) a.getY() - 1 * sqSize)
                    && isTurnOk((int) c.getX() + 1 * sqSize, (int) c.getY() + 1 * sqSize)
                    && isTurnOk((int) d.getX() + 2 * sqSize, (int) d.getY())) {
                isIf = true;
            }
        }

        return isIf;
    }

    public void turnEllFromPos3(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
        partLeft(a);
        partUp(a);
        partRight(c);
        partDown(c);
        partRight(d);
        partRight(d);
    }
    /**
     * Katsoo onko ell-neljännessä asennossa ja onko sillä tilaa kääntyä
     * viidenteen asentoon. Parametreinä blockin osat.
     * 
     * @return true jos on asennossa 4 ja on tilaa kääntyä.
     */
    public boolean ellTurnIf4(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
        boolean isIf = false;

        if (b.getX() < c.getX() && isTurnOk((int) a.getX() + 1 * sqSize, (int) a.getY() - 1 * sqSize)
                && isTurnOk((int) c.getX() - 1 * sqSize, (int) c.getY() + 1 * sqSize)
                && isTurnOk((int) d.getX(), (int) d.getY() + 2 * sqSize)) {
            isIf = true;
        }

        return isIf;
    }

    public void turnEllFromPos4(Rectangle a, Rectangle b, Rectangle c, Rectangle d) {
        partRight(a);
        partUp(a);
        partLeft(c);
        partDown(c);
        partDown(d);
        partDown(d);
    }

    /**
     * Metodi katsoo, onko blockin osan kääntämisen jälkeen tulevat uudet
     * kooridaatit ok. Ensimmäinen if tarkastaa, tuleeko laudan rajat vastaan.
     * Toinen if tarkastaa, onko kyseisessä ruudussa jo aiemman palikan osa.
     *
     * @param x blockin osan kääntämisen jälkeinen x-koordinaatti.
     * @param y blockin osan kääntämisen jälkeinen y-koordinaatti.
     *
     * @return true, jos blockin osan tuleva koordinaattio on käypä, false jos
     * ei.
     */
    public boolean isTurnOk(int x, int y) {
        boolean turnOk = true;
        //tuleeko X tai Y-koordinaatin rajat vastaan. ja sen jälkeen katsoo onko alla jotain.
        if (x > boardWidth | x < 0 | y > boardHeight | y < 0) {
            turnOk = false;

        } else if (board[x / sqSize][y / sqSize] == 1) {
            turnOk = false;
        }

        return turnOk;
    }

    /**
     * Metodi liikuttaa syötteenä annettua Blockin osaa ALAS yhden muuvin
     * verran.
     *
     * @param part liikuteltava blockin osa.
     */
    public void partDown(Rectangle part) {
        part.setY(part.getY() + move);
    }

    /**
     * Metodi liikuttaa syötteenä annettua Blockin osaa OIKEALLE yhden muuvin
     * verran.
     *
     * @param part liikuteltava blockin osa.
     */
    public void partRight(Rectangle part) {
        part.setX(part.getX() + move);
    }

    /**
     * Metodi liikuttaa syötteenä annettua Blockin osaa VASEMMALLE yhden muuvin
     * verran.
     *
     * @param part liikuteltava blockin osa.
     */
    public void partLeft(Rectangle part) {
        part.setX(part.getX() - move);
    }

    /**
     * Metodi liikuttaa syötteenä annettua Blockin osaa YLÖS yhden muuvin
     * verran.
     *
     * @param part liikuteltava blockin osa.
     */
    public void partUp(Rectangle part) {
        part.setY(part.getY() - move);
    }

    //Testausta varten
    public void setBoard(int[][] board) {
        this.board = board;
    }
}
