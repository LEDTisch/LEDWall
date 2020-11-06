class Level{
    public:
        Level(){

        }
    private:
        Player* player;
        byte boxsize=0x02;//die Kisten können verschieden groß sein 2*2 oder 1*1 oder vieleicht auch 3*3 boxsize ist meistens auch die stepsize
        byte stepsize=boxsize;
};