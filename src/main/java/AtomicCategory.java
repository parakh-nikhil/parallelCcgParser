public abstract class AtomicCategory extends Category{
    @Override
    public Boolean isSlash() {
        return false;
    }

    @Override
    public Category asSlash() throws Exception{
        throw new Exception("Not a Slash Type Category");
    }
}
