package be.koder.library.mutators;

public interface Mutator<C extends Command, P> {
    void execute(C command, P presenter);
}