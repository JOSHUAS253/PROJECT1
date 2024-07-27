/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package decoraterpatterndemo;
interface TextEditor {
    void display();
}

class BasicTextEditor implements TextEditor {
    @Override
    public void display() {
        System.out.println("Basic Text Editor");
    }
}

abstract class TextEditorDecorator implements TextEditor {
    protected TextEditor decoratedTextEditor;

    public TextEditorDecorator(TextEditor decoratedTextEditor) {
        this.decoratedTextEditor = decoratedTextEditor;
    }

    public void display() {
        decoratedTextEditor.display();
    }
}

class SpellCheckDecorator extends TextEditorDecorator {
    public SpellCheckDecorator(TextEditor decoratedTextEditor) {
        super(decoratedTextEditor);
    }

    @Override
    public void display() {
        decoratedTextEditor.display();
        System.out.println("Adding Spell Checking");
    }
}

class GrammarCheckDecorator extends TextEditorDecorator {
    public GrammarCheckDecorator(TextEditor decoratedTextEditor) {
        super(decoratedTextEditor);
    }

    @Override
    public void display() {
        decoratedTextEditor.display();
        System.out.println("Adding Grammar Checking");
    }
}

public class DecoratorPatternDemo {
    public static void main(String[] args) {
        TextEditor basicEditor = new BasicTextEditor();

        TextEditor spellCheckEditor = new SpellCheckDecorator(basicEditor);
        TextEditor grammarCheckEditor = new GrammarCheckDecorator(spellCheckEditor);

        System.out.println("Basic Editor:");
        basicEditor.display();

        System.out.println("\nSpell Check Editor:");
        spellCheckEditor.display();

        System.out.println("\nGrammar Check Editor:");
        grammarCheckEditor.display();
    }
}
