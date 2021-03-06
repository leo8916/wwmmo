package au.com.codeka.warworlds.planetrender;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import au.com.codeka.warworlds.common.Colour;
import au.com.codeka.warworlds.common.Image;

/**
 * This is actually a very simple ray-tracing engine. The simplicity comes from the fact that
 * we assume there's only one object in the scene (the planet) and one light source (the sun).
 */
public class PlanetRenderer {
  private ArrayList<SinglePlanetGenerator> mSinglePlanetGenerators;

  public PlanetRenderer(Template.PlanetTemplate tmpl, Random rand) {
    mSinglePlanetGenerators = new ArrayList<>();
    mSinglePlanetGenerators.add(new SinglePlanetGenerator(tmpl, rand));
  }

  public PlanetRenderer(Template.PlanetsTemplate tmpl, Random rand) {
    mSinglePlanetGenerators = new ArrayList<>();
    for (Template.PlanetTemplate planetTmpl : tmpl.getParameters(Template.PlanetTemplate.class)) {
      mSinglePlanetGenerators.add(new SinglePlanetGenerator(planetTmpl, rand));
    }
  }

  /**
   * Renders a planet into the given {@link Image}.
   */
  public void render(Image img) {
    int i = 0;
    for (SinglePlanetGenerator planetGenerator : mSinglePlanetGenerators) {
      for (int y = 0; y < img.getHeight(); y++) {
        for (int x = 0; x < img.getWidth(); x++) {
          double nx = ((double) x / (double) img.getWidth()) - 0.5;
          double ny = ((double) y / (double) img.getHeight()) - 0.5;
          Colour c = planetGenerator.getPixelColour(nx, ny);
          if (i == 0) {
            img.setPixelColour(x, y, c);
          } else {
            img.blendPixelColour(x, y, c);
          }
        }
      }
      i++;
    }
  }

  /**
   * Renders a planet into the given {@link BufferedImage}.
   */
  public void render(BufferedImage img) {
    int i = 0;
    for (SinglePlanetGenerator planetGenerator : mSinglePlanetGenerators) {
      for (int y = 0; y < img.getHeight(); y++) {
        for (int x = 0; x < img.getWidth(); x++) {
          double nx = ((double) x / (double) img.getWidth()) - 0.5;
          double ny = ((double) y / (double) img.getHeight()) - 0.5;
          Colour c = planetGenerator.getPixelColour(nx, ny);
          if (i == 0) {
            img.setRGB(x, y, c.toArgb());
          } else {
            Colour imgColour = new Colour(img.getRGB(x, y));
            Colour.blend(imgColour, c);
            img.setRGB(x, y, imgColour.toArgb());
          }
        }
      }
      i++;
    }
  }
}

